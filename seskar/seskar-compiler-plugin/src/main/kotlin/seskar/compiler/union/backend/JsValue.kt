package seskar.compiler.union.backend

import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_INT_VALUE = FqName("seskar.js.JsIntValue")
private val JS_VALUE = FqName("seskar.js.JsValue")

private inline fun <reified T> IrConstructorCall.value(): T {
    val argument = getValueArgument(0) as IrConst<*>
    return argument.value as T
}

internal fun IrAnnotationContainer.value(): Value? {
    val jsInt = getAnnotation(JS_INT_VALUE)
    if (jsInt != null) {
        return IntValue(jsInt.value<Int>())
    }

    val jsString = getAnnotation(JS_VALUE)
    if (jsString != null) {
        return StringValue(jsString.value<String>())
    }

    return null
}
