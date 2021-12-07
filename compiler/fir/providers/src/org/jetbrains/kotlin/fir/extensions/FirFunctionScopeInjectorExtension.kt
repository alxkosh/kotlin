package org.jetbrains.kotlin.fir.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.validate
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol
import org.jetbrains.kotlin.name.Name
import kotlin.reflect.KClass

abstract class FirFunctionScopeInjectorExtension(session: FirSession) : FirExtension(session) {
    companion object {
        val NAME = FirExtensionPointName("FunctionScopeInjector")
    }

    final override val name: FirExtensionPointName
        get() = NAME

    final override val extensionType: KClass<out FirExtension> = FirFunctionScopeInjectorExtension::class

    protected val _topLevelGeneratedDeclarations: MutableSet<FirDeclaration> = mutableSetOf()
    val topLevelGeneratedDeclarations: Set<FirDeclaration>
        get() = _topLevelGeneratedDeclarations

    open fun getPropertySymbols(function: FirFunction, name: Name): List<FirVariableSymbol<*>> =
        collectSymbols(function, name, ::generatePropertySymbols, ::providePropertySymbols)

    open fun getFunctionSymbols(function: FirFunction, name: Name): List<FirNamedFunctionSymbol> =
        collectSymbols(function, name, ::generateFunctionSymbols, ::provideFunctionSymbols)

    open fun getClassLikeSymbols(function: FirFunction, name: Name): List<FirClassLikeSymbol<*>> =
        collectSymbols(function, name, ::generateClassLikeSymbols, ::provideClassLikeSymbols)

    private fun <T : FirBasedSymbol<*>> collectSymbols(
        function: FirFunction,
        name: Name,
        generator: (FirFunction, Name) -> List<T>,
        provider: (FirFunction, Name) -> List<T>
    ): List<T> {
        val generatedSymbols = generator(function, name).onEach { it.fir.validate() }
        val providedSymbols = provider(function, name)
        _topLevelGeneratedDeclarations += generatedSymbols.map { it.fir }
        return generatedSymbols + providedSymbols
    }

    protected open fun generatePropertySymbols(function: FirFunction, name: Name): List<FirVariableSymbol<*>> = emptyList()
    protected open fun providePropertySymbols(function: FirFunction, name: Name): List<FirVariableSymbol<*>> = emptyList()

    protected open fun generateFunctionSymbols(function: FirFunction, name: Name): List<FirNamedFunctionSymbol> = emptyList()
    protected open fun provideFunctionSymbols(function: FirFunction, name: Name): List<FirNamedFunctionSymbol> = emptyList()

    protected open fun generateClassLikeSymbols(function: FirFunction, name: Name): List<FirClassLikeSymbol<*>> = emptyList()
    protected open fun provideClassLikeSymbols(function: FirFunction, name: Name): List<FirClassLikeSymbol<*>> = emptyList()

    abstract fun needInjectInto(function: FirFunction): Boolean

    open fun getCallableNames(function: FirFunction): Set<Name> = emptySet()
    open fun getClassifierNames(function: FirFunction): Set<Name> = emptySet()

    fun interface Factory : FirExtension.Factory<FirFunctionScopeInjectorExtension>
}

val FirExtensionService.functionScopeInjectors: List<FirFunctionScopeInjectorExtension> by FirExtensionService.registeredExtensions()