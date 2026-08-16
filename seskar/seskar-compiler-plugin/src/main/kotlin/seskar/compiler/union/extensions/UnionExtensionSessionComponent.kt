package seskar.compiler.union.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.FirExtensionSessionComponent

class UnionExtensionSessionComponent(session: FirSession) :
    FirExtensionSessionComponent(session) {

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(UnionPredicates.AnnotatedWithUnion.DECLARATION)
    }
}
