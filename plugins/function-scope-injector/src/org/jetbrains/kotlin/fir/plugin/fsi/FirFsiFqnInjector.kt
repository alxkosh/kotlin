package org.jetbrains.kotlin.fir.plugin.fsi

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.FirPluginKey
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirConstExpression
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression
import org.jetbrains.kotlin.fir.expressions.arguments
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.FirFunctionScopeInjectorExtension
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.has
import org.jetbrains.kotlin.fir.extensions.predicate.metaHas
import org.jetbrains.kotlin.fir.extensions.predicate.or
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.resolve.providers.symbolProvider
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.name.*

class FirFsiFqnInjector(session: FirSession) : FirFunctionScopeInjectorExtension(session) {
    companion object {
        private val ANNOTATION = "InjectByFqn".fqn()
        private val ANNOTATION_CLASS_ID = ClassId(ANNOTATION.parent(), ANNOTATION.shortName())
        private val NAME_ARGUMENT = Name.identifier("name")
        private val FQN_ARGUMENT = Name.identifier("fqn")
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

    private fun getNamesFromAnnotations(function: FirFunctionSymbol<FirFunction>): Map<Name, List<FqName>> {
        return buildMap<Name, MutableList<FqName>> {
            val annotations = findAnnotationsByClassId(function.annotations, ANNOTATION_CLASS_ID)
            for (annotation in annotations) {
                val annotationCall = annotation as? FirAnnotationCall ?: continue
                val arguments = annotationCall.arguments.mapNotNull { it as? FirNamedArgumentExpression }
                val name = arguments.find { it.name == NAME_ARGUMENT }?.expression as? FirConstExpression<*> ?: continue
                val fqn = arguments.find { it.name == FQN_ARGUMENT }?.expression as? FirConstExpression<*> ?: continue
                getOrPut(Name.identifier(name.value as String), ::mutableListOf).add(FqName(fqn.value as String))
            }
        }
    }

    override fun providePropertySymbols(function: FirFunction, name: Name): List<FirVariableSymbol<*>> {
        return namesByFunction[function.symbol]?.get(name)?.flatMap {
            val (packageName, tail) = extractPackageName(it)
            if (tail.isRoot) return emptyList()
            if (tail.parent().isRoot) {
                session.symbolProvider.getTopLevelPropertySymbols(packageName, tail.shortName())
            } else {
                val (classSymbol, propertyFqName) = extractClass(packageName, tail)
                if (classSymbol == null || !propertyFqName.isOneSegmentFQN()) return emptyList()
                return when (classSymbol) {
                    is FirRegularClassSymbol -> {
                        val properties = mutableListOf<FirVariableSymbol<*>>()
                        findPropertiesInClassTo(properties, classSymbol, propertyFqName.shortName())
                        properties
                    }
                    else -> emptyList()
                }
            }
        }.orEmpty()
    }

    private fun findPropertiesInClassTo(symbols: MutableList<FirVariableSymbol<*>>, classSymbol: FirRegularClassSymbol, name: Name) {
        classSymbol.declarationSymbols.forEach {
            when(it) {
                is FirEnumEntrySymbol -> if (it.name == name) symbols.add(it)
                // commented out due to IllegalStateException
                // in org/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator.kt:740 FirQualifiedAccess.findIrReceiver
                //is FirPropertySymbol -> if (it.name == name) symbols.add(it)
                /*is FirRegularClassSymbol -> {
                    if (it.isCompanion) {
                        findPropertiesInClassTo(symbols, it, name)
                    }
                }*/
            }
        }
    }

    override fun provideFunctionSymbols(function: FirFunction, name: Name): List<FirNamedFunctionSymbol> {
        return namesByFunction[function.symbol]?.get(name)?.flatMap {
            val (packageName, tail) = extractPackageName(it)
            if (!tail.isOneSegmentFQN()) return emptyList()
            session.symbolProvider.getTopLevelFunctionSymbols(packageName, tail.shortName())
        }.orEmpty()
    }

    override fun provideClassLikeSymbols(function: FirFunction, name: Name): List<FirClassLikeSymbol<*>> {
        return namesByFunction[function.symbol]?.get(name)?.mapNotNull {
            val (packageName, tail) = extractPackageName(it)
            val classSymbol = session.symbolProvider.getClassLikeSymbolByClassId(ClassId(packageName, tail, false))
            classSymbol
        }.orEmpty()
    }

    private fun extractPackageName(fqName: FqName): Pair<FqName, FqName> {
        val symbolProvider = session.symbolProvider
        var currentPackage = fqName.parent()
        while (!currentPackage.isRoot) {
            if (symbolProvider.getPackage(currentPackage) != null) {
                break
            }
            currentPackage = currentPackage.parent()
        }
        return currentPackage to fqName.tail(currentPackage)
    }

    private fun extractClass(packageName: FqName, fqName: FqName): Pair<FirClassLikeSymbol<*>?, FqName> {
        val symbolProvider = session.symbolProvider
        var relativeClassName = fqName
        while(!relativeClassName.isRoot) {
            val classId = ClassId(packageName, relativeClassName, false)
            val symbol = symbolProvider.getClassLikeSymbolByClassId(classId)
            if (symbol != null) {
                return symbol to fqName.tail(relativeClassName)
            }
            relativeClassName = relativeClassName.parent()
        }
        return null to fqName
    }

    override fun getCallableNames(function: FirFunction): Set<Name> {
        return namesByFunction[function.symbol]?.keys ?: emptySet()
    }

    override fun getClassifierNames(function: FirFunction): Set<Name> = getCallableNames(function)

    override fun needInjectInto(function: FirFunction): Boolean = matchedFunctions.contains(function.symbol)

    object Key : FirPluginKey()

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(PREDICATE)
    }

}