package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.declarations.utils.isActual
import org.jetbrains.kotlin.fir.declarations.utils.isExpect
import org.jetbrains.kotlin.fir.extensions.ExperimentalSupertypesGenerationApi
import org.jetbrains.kotlin.fir.extensions.FirSupertypeGenerationExtension
import org.jetbrains.kotlin.fir.types.*
import org.jetbrains.kotlin.platform.WasmPlatform
import seskar.compiler.common.backend.isReallyExternal

internal class JsAnySupertypeGenerationExtension(session: FirSession) :
    FirSupertypeGenerationExtension(session) {
    override fun needTransformSupertypes(
        declaration: FirClassLikeDeclaration,
    ): Boolean {
        if (!isReallyExternal(declaration, session))
            return false

        if (declaration.isExpect)
            return false

        if (declaration.isActual)
            return false

        return declaration.moduleData.isCommon
                && declaration.moduleData.platform.any { it is WasmPlatform }
    }

    override fun computeAdditionalSupertypes(
        classLikeDeclaration: FirClassLikeDeclaration,
        resolvedSupertypes: List<FirResolvedTypeRef>,
        typeResolver: TypeResolveService,
    ): List<ConeKotlinType> =
        if (isJsMarkerRequired(resolvedSupertypes)) {
            listOf(jsAnyClassLikeType())
        } else emptyList()

    @ExperimentalSupertypesGenerationApi
    override fun computeAdditionalSupertypesForGeneratedNestedClass(
        klass: FirRegularClass,
        typeResolver: TypeResolveService,
    ): List<ConeKotlinType> =
        if (isJsMarkerRequired(klass.superTypeRefs)) {
            listOf(jsAnyClassLikeType())
        } else emptyList()

    @JvmName("isJsMarkerRequiredFirResolvedTypeRef")
    private fun isJsMarkerRequired(
        supertypes: List<FirResolvedTypeRef>,
    ): Boolean =
        isJsMarkerRequired(supertypes) { it.coneType }

    @JvmName("isJsMarkerRequiredFirTypeRef")
    private fun isJsMarkerRequired(
        supertypes: List<FirTypeRef>,
    ): Boolean =
        isJsMarkerRequired(supertypes) { it.coneTypeOrNull }

    private fun <T : Any> isJsMarkerRequired(
        supertypes: List<T>,
        getConeType: (T) -> ConeKotlinType?,
    ): Boolean {
        if (supertypes.isEmpty())
            return true

        val parent = supertypes.singleOrNull()
            ?.let { getConeType(it) }
            ?: return false

        return parent.isAny
    }
}
