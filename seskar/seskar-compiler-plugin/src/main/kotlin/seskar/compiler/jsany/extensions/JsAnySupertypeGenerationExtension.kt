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
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.platform.JsPlatform
import org.jetbrains.kotlin.platform.WasmPlatform

private val JS_ANY_MARKER = ClassId(FqName("js.core"), Name.identifier("JsAnyMarker"))

internal class JsAnySupertypeGenerationExtension(session: FirSession) :
    FirSupertypeGenerationExtension(session) {
    override fun needTransformSupertypes(
        declaration: FirClassLikeDeclaration,
    ): Boolean {
        if (!declaration.isExternal)
            return false

        if (declaration.isExpect || declaration.isActual)
            return false

        val platform = declaration.moduleData.platform
        return when (platform.size) {
            1 -> platform.any { it is JsPlatform }
            2 -> platform.any { it is JsPlatform } && platform.any { it is WasmPlatform }
            else -> false
        }
    }

    override fun computeAdditionalSupertypes(
        classLikeDeclaration: FirClassLikeDeclaration,
        resolvedSupertypes: List<FirResolvedTypeRef>,
        typeResolver: TypeResolveService,
    ): List<ConeKotlinType> {
        if (resolvedSupertypes.isNotEmpty())
            return emptyList()

        return listOf(
            JS_ANY_MARKER.constructClassLikeType(),
        )
    }
}
