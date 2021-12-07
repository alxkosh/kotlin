package org.jetbrains.kotlin.fir.plugin.fsi

import org.jetbrains.kotlin.descriptors.EffectiveVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirProperty
import org.jetbrains.kotlin.fir.declarations.builder.buildProperty
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirConstExpression
import org.jetbrains.kotlin.fir.extensions.FirFunctionScopeInjectorExtension
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.resolve.toSymbol
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.expectedConeType
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.fir.types.ConeClassLikeType
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef
import org.jetbrains.kotlin.fir.types.coneTypeSafe
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

fun String.fqn(): FqName = FqName("org.jetbrains.kotlin.fir.plugin.fsi.$this")

fun FirFunctionScopeInjectorExtension.findAnnotationsByClassId(
    annotations: List<FirAnnotation>,
    classId: ClassId
): List<FirAnnotation> {
    val result = mutableListOf<FirAnnotation>()
    processAnnotations(
        annotations,
        classId,
        session,
        result,
        mutableSetOf()
    )
    return result
}

private fun processAnnotations(
    annotations: List<FirAnnotation>,
    classId: ClassId,
    session: FirSession,
    result: MutableList<FirAnnotation>,
    visited: MutableSet<FirAnnotation>
) {
    for (annotation in annotations) {
        if (!visited.add(annotation)) continue
        val lookupTag = annotation.annotationTypeRef.coneTypeSafe<ConeClassLikeType>()?.lookupTag ?: continue
        if (lookupTag.classId == classId) result.add(annotation)
        lookupTag.toSymbol(session)?.annotations?.let {
            processAnnotations(it, classId, session, result, visited)
        }
    }
}

fun <T> FirFunctionScopeInjectorExtension.buildTopLevelConstProperty(
    name: Name,
    propertyTypeRef: FirImplicitBuiltinTypeRef,
    initializingConstExpression: FirConstExpression<T>,
    origin: FirDeclarationOrigin,
    packageName: FqName = FqName.ROOT
): FirProperty {
    return buildProperty {
        moduleData = session.moduleData
        this.origin = origin
        status = FirResolvedDeclarationStatusImpl(
            Visibilities.Public,
            Modality.FINAL,
            EffectiveVisibility.Public
        )
        isVar = false
        isLocal = false
        returnTypeRef = propertyTypeRef
        initializer = initializingConstExpression.apply {
            val typeRef = buildResolvedTypeRef {
                this.type = kind.expectedConeType(session)
            }
            replaceTypeRef(typeRef)
        }
        val firPropertySymbol = FirPropertySymbol(CallableId(packageName, name))
        getter = FirDefaultPropertyGetter(
            source = null,
            moduleData = session.moduleData,
            origin = origin,
            propertyTypeRef = propertyTypeRef,
            visibility = Visibilities.Public,
            propertySymbol = firPropertySymbol,
            effectiveVisibility = EffectiveVisibility.Public
        )
        symbol = firPropertySymbol
        this.name = name
    }
}