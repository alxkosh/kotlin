package org.jetbrains.kotlin.fir.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMap
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
import org.jetbrains.kotlin.fir.scopes.impl.toConeType
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.utils.addToStdlib.flatGroupBy

class FirExtensionFunctionScope private constructor(
    private val extensionsByCallableName: Map<Name, List<FirFunctionScopeInjectorExtension>>,
    private val extensionsByClassifierName: Map<Name, List<FirFunctionScopeInjectorExtension>>,
    private val allCallableNames: Set<Name>,
    private val allClassifierNames: Set<Name>,
    private val function: FirFunction,
    private val useSiteSession: FirSession
) : FirContainingNamesAwareScope() {
    companion object {
        fun create(function: FirFunction, session: FirSession): FirExtensionFunctionScope? {
            val extensions = session.extensionService.functionScopeInjectors.filter { it.needInjectInto(function) }
            if (extensions.isEmpty()) return null
            val extensionsByCallableName = extensions.flatGroupBy { it.getCallableNames(function) }
            val extensionsByClassifierName = extensions.flatGroupBy { it.getClassifierNames(function) }
            val allCallableNames = extensionsByCallableName.keys
            val allClassifierNames = extensionsByClassifierName.keys
            return FirExtensionFunctionScope(
                extensionsByCallableName,
                extensionsByClassifierName,
                allCallableNames,
                allClassifierNames,
                function,
                session
            )
        }
    }

    private val properties = mutableMapOf<Name, List<FirVariableSymbol<*>>>()
    private val functions = mutableMapOf<Name, List<FirNamedFunctionSymbol>>()
    private val classes = mutableMapOf<Name, List<FirClassLikeSymbol<*>>>()

    override fun processPropertiesByName(name: Name, processor: (FirVariableSymbol<*>) -> Unit) {
        val symbols = properties.getOrPut(name) {
            extensionsByCallableName[name].orEmpty().flatMap {
                it.getPropertySymbols(function, name)
            }
        }
        for (symbol in symbols) {
            processor(symbol)
        }
    }

    override fun processFunctionsByName(name: Name, processor: (FirNamedFunctionSymbol) -> Unit) {
        val symbols = functions.getOrPut(name) {
            extensionsByCallableName[name].orEmpty().flatMap {
                it.getFunctionSymbols(function, name)
            }
        }
        for (symbol in symbols) {
            processor(symbol)
        }
    }

    override fun processClassifiersByNameWithSubstitution(name: Name, processor: (FirClassifierSymbol<*>, ConeSubstitutor) -> Unit) {
        val symbols = classes.getOrPut(name) {
            extensionsByClassifierName[name].orEmpty().flatMap {
                it.getClassLikeSymbols(function, name)
            }
        }
        for (symbol in symbols) {
            val typeParameter = when (symbol) {
                is FirClassSymbol<*> -> symbol.typeParameterSymbols
                is FirTypeAliasSymbol -> symbol.typeParameterSymbols
                else -> null
            }
            val substitution = typeParameter?.associateWith { it.toConeType() }
            val coneSubstitutor = substitution?.let { ConeSubstitutorByMap(it, useSiteSession) } ?: ConeSubstitutor.Empty
            processor(symbol, coneSubstitutor)
        }
    }

    override fun mayContainName(name: Name): Boolean {
        return allCallableNames.contains(name) || allClassifierNames.contains(name)
    }

    override fun getCallableNames(): Set<Name> = allCallableNames
    override fun getClassifierNames(): Set<Name> = allClassifierNames
}