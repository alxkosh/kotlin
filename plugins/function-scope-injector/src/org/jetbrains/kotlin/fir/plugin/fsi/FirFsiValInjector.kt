package org.jetbrains.kotlin.fir.plugin.fsi

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.caches.FirCache
import org.jetbrains.kotlin.fir.caches.firCachesFactory
import org.jetbrains.kotlin.fir.caches.getValue
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.FirPluginKey
import org.jetbrains.kotlin.fir.declarations.FirProperty
import org.jetbrains.kotlin.fir.expressions.*
import org.jetbrains.kotlin.fir.expressions.builder.buildConstExpression
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.FirFunctionScopeInjectorExtension
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.has
import org.jetbrains.kotlin.fir.extensions.predicate.metaHas
import org.jetbrains.kotlin.fir.extensions.predicate.or
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.references.FirNamedReference
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.ConstantValueKind
import java.util.concurrent.ConcurrentHashMap

class FirFsiValInjector(session: FirSession) : FirFunctionScopeInjectorExtension(session) {
    companion object {
        private val ANNOTATION = "InjectVal".fqn()
        private val ANNOTATION_CLASS_ID = ClassId(ANNOTATION.parent(), ANNOTATION.shortName())
        private val TYPE_ARGUMENT = Name.identifier("type")
        private val NAME_ARGUMENT = Name.identifier("name")
        private val VALUE_ARGUMENT = Name.identifier("value")
        private val PREDICATE: DeclarationPredicate = has(ANNOTATION) or metaHas(ANNOTATION)
    }

    private val matchedFunctions by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(PREDICATE).filterIsInstance<FirFunctionSymbol<FirFunction>>()
    }

    private val namesByFunction by lazy {
        buildMap {
            matchedFunctions.forEach {
                put(it, getNamesFromAnnotations(it))
            }
        }
    }

    private val propertyCache: FirCache<Variable<*>, FirProperty, Nothing?> = session.firCachesFactory.createCache { variable, _ ->
        buildTopLevelConstProperty(
            name = generatePropertyName(variable),
            propertyTypeRef = variable.type.typeRef,
            initializingConstExpression = variable.type.buildExpression(variable.value),
            origin = Key.origin
        )
    }

    private fun getNamesFromAnnotations(function: FirFunctionSymbol<FirFunction>): Map<Name, List<Variable<*>>> {
        return buildMap<Name, MutableList<Variable<*>>> {
            val annotations = findAnnotationsByClassId(function.annotations, ANNOTATION_CLASS_ID)
            for (annotation in annotations) {
                val annotationCall = annotation as? FirAnnotationCall ?: continue
                val arguments = annotationCall.arguments.mapNotNull { it as? FirNamedArgumentExpression }
                val type = arguments.find { it.name == TYPE_ARGUMENT }?.expression as? FirPropertyAccessExpression ?: continue
                val typeReference = type.calleeReference as? FirNamedReference ?: continue
                val variableType = VariableType.getVariableType(typeReference.name, session)
                val name = arguments.find { it.name == NAME_ARGUMENT }?.expression as? FirConstExpression<*> ?: continue
                val value = arguments.find { it.name == VALUE_ARGUMENT }?.expression as? FirConstExpression<*> ?: continue
                getOrPut(Name.identifier(name.value as String), ::mutableListOf).add(
                    Variable(value.value as String, variableType)
                )
            }
        }
    }

    override fun generatePropertySymbols(function: FirFunction, name: Name): List<FirPropertySymbol> {
        return namesByFunction[function.symbol]?.get(name)?.map {
            propertyCache.getValue(it).symbol
        }.orEmpty()
    }

    private fun generatePropertyName(variable: Variable<*>): Name {
        return Name.identifier("\$fsi_${variable.toString().sanitizeAsJavaIdentifier()}")
    }

    private fun String.sanitizeAsJavaIdentifier(): String {
        return buildString {
            for(ch in this@sanitizeAsJavaIdentifier) {
                when {
                    ch == '$' -> append("$$")
                    ch.isJavaIdentifierPart() -> append(ch)
                    else -> {
                        append('$')
                        append(ch.code)
                        append('$')
                    }
                }
            }
        }
    }

    /*private fun generatePropertyName(function: FirFunctionSymbol<out FirFunction>, name: Name): Name {
        return Name.identifier(
            buildString {
                append("\$FSI$")
                append(function.callableId.asSingleFqName().asString())
                append('$')
                function.valueParameterSymbols.forEach {
                    when (val type = it.resolvedReturnTypeRef.type) {
                        is ConeClassLikeType -> append(type.lookupTag.classId.asSingleFqName().asString())
                        is ConeLookupTagBasedType -> append(type.lookupTag.name.identifier)
                        else -> error("Unexpected value parameter type: $type")
                    }
                    append('$')
                }
                append(name.identifier)
            }.replace(".", "_")
        )
    }*/

    override fun getCallableNames(function: FirFunction): Set<Name> {
        return namesByFunction[function.symbol]?.keys ?: emptySet()
    }

    override fun needInjectInto(function: FirFunction): Boolean = matchedFunctions.contains(function.symbol)

    object Key : FirPluginKey()

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(PREDICATE)
    }

    private data class Variable<T>(
        val value: String,
        val type: VariableType<T>
    ) {
        override fun toString(): String = "${type}_$value"
    }

    private abstract class VariableType<T>(val typeRef: FirImplicitBuiltinTypeRef) {
        companion object {
            val types = ConcurrentHashMap<String, VariableType<*>>()

            fun getVariableType(typeName: Name, session: FirSession): VariableType<*> {
                return types.getOrPut(typeName.identifier) {
                    when (typeName.identifier) {
                        "Boolean" -> object : VariableType<Boolean>(session.builtinTypes.booleanType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Boolean, value.toBoolean(), setType = true)
                        }

                        "Byte" -> object : VariableType<Byte>(session.builtinTypes.byteType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Byte, value.toByte(), setType = true)
                        }

                        "Short" -> object : VariableType<Short>(session.builtinTypes.shortType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Short, value.toShort(), setType = true)
                        }

                        "Int" -> object : VariableType<Int>(session.builtinTypes.intType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Int, value.toInt(), setType = true)
                        }

                        "Long" -> object : VariableType<Long>(session.builtinTypes.longType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Long, value.toLong(), setType = true)
                        }

                        "Float" -> object : VariableType<Float>(session.builtinTypes.floatType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Float, value.toFloat(), setType = true)
                        }

                        "Double" -> object : VariableType<Double>(session.builtinTypes.doubleType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Double, value.toDouble(), setType = true)
                        }

                        "Char" -> object : VariableType<Char>(session.builtinTypes.charType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.Char, value.single(), setType = true)
                        }

                        "String" -> object : VariableType<String>(session.builtinTypes.stringType) {
                            override fun buildExpression(value: String) =
                                buildConstExpression(null, ConstantValueKind.String, value, setType = true)
                        }

                        else -> error("Unexpected name of the injected val type: $typeName")
                    }
                }
            }
        }

        abstract fun buildExpression(value: String): FirConstExpression<T>

        override fun toString(): String = typeRef.id.shortClassName.identifier
    }

}