package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.fir.types.ConeClassLikeType
import org.jetbrains.kotlin.fir.types.constructClassLikeType
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val JS_ANY = ClassId(
    FqName("kotlin.js"),
    Name.identifier("JsAny"),
)

internal fun jsAnyClassLikeType(): ConeClassLikeType =
    JS_ANY.constructClassLikeType()
