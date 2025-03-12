package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
import org.jetbrains.kotlin.fir.declarations.utils.isExternal
import org.jetbrains.kotlin.fir.extensions.FirSupertypeGenerationExtension
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.constructClassLikeType
import org.jetbrains.kotlin.fir.types.isAny
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.platform.JsPlatform

private val JS_ANY_MARKER = ClassId(FqName("js.core"), Name.identifier("JsAnyMarker"))

internal class JsAnySupertypeGenerationExtension(session: FirSession) :
    FirSupertypeGenerationExtension(session) {
    override fun needTransformSupertypes(
        declaration: FirClassLikeDeclaration,
    ): Boolean {
        if (!declaration.isExternal)
            return false

        val moduleData = declaration.moduleData
        return moduleData.isCommon
                && moduleData.platform.any { it is JsPlatform }
    }

    override fun computeAdditionalSupertypes(
        classLikeDeclaration: FirClassLikeDeclaration,
        resolvedSupertypes: List<FirResolvedTypeRef>,
        typeResolver: TypeResolveService,
    ): List<ConeKotlinType> =
        if (isMarkerRequired(resolvedSupertypes)) {
            listOf(JS_ANY_MARKER.constructClassLikeType())
        } else emptyList()

    private fun isMarkerRequired(
        resolvedSupertypes: List<FirResolvedTypeRef>,
    ): Boolean {
        if (resolvedSupertypes.isEmpty())
            true

        val parent = resolvedSupertypes.singleOrNull()
            ?: return false

        return parent.coneType.isAny
    }
}
