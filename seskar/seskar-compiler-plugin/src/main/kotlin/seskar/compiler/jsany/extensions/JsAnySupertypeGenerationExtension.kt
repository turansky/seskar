package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
import org.jetbrains.kotlin.fir.declarations.utils.isActual
import org.jetbrains.kotlin.fir.declarations.utils.isExpect
import org.jetbrains.kotlin.fir.declarations.utils.isExternal
import org.jetbrains.kotlin.fir.extensions.FirSupertypeGenerationExtension
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.constructClassLikeType
import org.jetbrains.kotlin.fir.types.isAny
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.platform.WasmPlatform

private val JS_CORE = FqName("js.core")

private val JS_ANY_MARKER = ClassId(
    JS_CORE,
    Name.identifier("JsAnyMarker"),
)

internal class JsAnySupertypeGenerationExtension(session: FirSession) :
    FirSupertypeGenerationExtension(session) {
    override fun needTransformSupertypes(
        declaration: FirClassLikeDeclaration,
    ): Boolean {
        if (!declaration.isExternal)
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
