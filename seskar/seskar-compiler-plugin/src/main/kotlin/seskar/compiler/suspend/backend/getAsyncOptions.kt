package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.name.FqName

private val JS_ASYNC = FqName("seskar.js.JsAsync")

internal fun IrFunction.getAsyncOptions(): AsyncOptions {
    require(hasAnnotation(JS_ASYNC)) {
        "Missed @JsAsync annotation for `${kotlinFqName.asString()}`"
    }

    return AsyncOptions(false)
}
