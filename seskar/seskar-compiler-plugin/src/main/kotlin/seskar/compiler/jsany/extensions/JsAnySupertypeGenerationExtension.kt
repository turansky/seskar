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
import org.jetbrains.kotlin.platform.isJs

private val JS_ANY_MARKER = ClassId(FqName("js.core"), Name.identifier("JsAnyMarker"))

internal class JsAnySupertypeGenerationExtension(session: FirSession) :
    FirSupertypeGenerationExtension(session) {
    override fun needTransformSupertypes(
        declaration: FirClassLikeDeclaration,
    ): Boolean {
        if (!declaration.isExternal)
            return false

        return declaration.moduleData.isCommon
                || declaration.moduleData.platform.isJs()
    }

    override fun computeAdditionalSupertypes(
        classLikeDeclaration: FirClassLikeDeclaration,
        resolvedSupertypes: List<FirResolvedTypeRef>,
        typeResolver: TypeResolveService,
    ): List<ConeKotlinType> {
        if (resolvedSupertypes.isNotEmpty()) {
            val parent = resolvedSupertypes.singleOrNull()
            if (parent == null || !parent.coneType.isAny) {
                return emptyList()
            }
        }

        return listOf(
            JS_ANY_MARKER.constructClassLikeType(),
        )
    }
}
