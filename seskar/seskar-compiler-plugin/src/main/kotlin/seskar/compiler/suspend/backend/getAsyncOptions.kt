package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.ir.util.getValueArgument
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val JS_ASYNC = FqName("seskar.js.JsAsync")

private val OPTIONAL = Name.identifier("optional")
private fun IrConstructorCall.value(
    name: Name,
    defaultValue: Boolean = false,
): Boolean {
    val argument = getValueArgument(name) as IrConst<*>?
        ?: return defaultValue

    return argument.value as Boolean?
        ?: defaultValue
}

internal fun IrFunction.getAsyncOptions(): AsyncOptions {
    val async = getAnnotation(JS_ASYNC)

    requireNotNull(async) {
        "Missed @JsAsync annotation for `${kotlinFqName.asString()}`"
    }

    return AsyncOptions(
        optional = async.value(OPTIONAL),
    )
}
