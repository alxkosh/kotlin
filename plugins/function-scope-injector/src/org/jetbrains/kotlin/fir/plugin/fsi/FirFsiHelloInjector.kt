package org.jetbrains.kotlin.fir.plugin.fsi

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.FirPluginKey
import org.jetbrains.kotlin.fir.expressions.builder.buildConstExpression
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.FirFunctionScopeInjectorExtension
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.has
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.ConstantValueKind

class FirFsiHelloInjector(session: FirSession) : FirFunctionScopeInjectorExtension(session) {
    companion object {
        private val PROPERTY_NAME: Name = Name.identifier("HELLO")
        private const val PROPERTY_VALUE: String = "Hello!"
        private val GENERATED_NAME: Name = Name.identifier("\$fsi_withHello_${PROPERTY_NAME.identifier}")
        private val PREDICATE: DeclarationPredicate = has("WithHello".fqn())
    }

    private val matchedFunctions by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(PREDICATE).filterIsInstance<FirFunctionSymbol<FirFunction>>()
    }

    private val propertySymbols: List<FirPropertySymbol> by lazy {
        listOf(
            buildTopLevelConstProperty(
                name = GENERATED_NAME,
                propertyTypeRef = session.builtinTypes.stringType,
                initializingConstExpression = buildConstExpression(null, ConstantValueKind.String, PROPERTY_VALUE, setType = true),
                origin = Key.origin
            ).symbol
        )
    }

    override fun getCallableNames(function: FirFunction): Set<Name> {
        if (function.symbol !in matchedFunctions) return emptySet()
        return setOf(PROPERTY_NAME)
    }

    override fun generatePropertySymbols(function: FirFunction, name: Name): List<FirPropertySymbol> {
        if (name != PROPERTY_NAME) return emptyList()
        return propertySymbols
    }

    override fun needInjectInto(function: FirFunction): Boolean = matchedFunctions.contains(function.symbol)

    object Key : FirPluginKey()

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(PREDICATE)
    }

}