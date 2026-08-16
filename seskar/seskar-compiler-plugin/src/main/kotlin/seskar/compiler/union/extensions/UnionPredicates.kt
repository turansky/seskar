package seskar.compiler.union.extensions

import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate
import org.jetbrains.kotlin.name.FqName

object UnionPredicates {
    private val JS_UNION: FqName = FqName("js.union.JsUnion")

    internal object AnnotatedWithUnion {
        val LOOKUP = LookupPredicate.create { annotated(JS_UNION) }
        val DECLARATION = DeclarationPredicate.create { annotated(JS_UNION) }
    }
}
