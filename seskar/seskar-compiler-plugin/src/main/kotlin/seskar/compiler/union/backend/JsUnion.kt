package seskar.compiler.union.backend

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.companionObject
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_UNION = FqName("seskar.js.JsUnion")

private val JS_INT_VALUE = FqName("seskar.js.JsIntValue")
private val JS_VALUE = FqName("seskar.js.JsValue")

private fun jsValue(i: Int): String = "$i"

private fun jsValue(s: String): String = "'$s'"

private val IrDeclarationWithName.id: String
    get() = name.identifier

private inline fun <reified T> IrConstructorCall.value(): T {
    val argument = getValueArgument(0) as IrConst<*>
    return argument.value as T
}

private fun IrDeclarationWithName.value(): String {
    val jsInt = getAnnotation(JS_INT_VALUE)
    if (jsInt != null) {
        return jsValue(jsInt.value<Int>())
    }

    val jsString = getAnnotation(JS_VALUE)
    if (jsString != null) {
        return jsValue(jsString.value<String>())
    }

    return jsValue(id)
}

internal fun IrClass.toJsUnionBody(): String? {
    if (!isExternal)
        return null

    if (kind != ClassKind.INTERFACE)
        return null

    getAnnotation(JS_UNION)
        ?: return null

    val companion = companionObject()
        ?: return null

    return companion.declarations.asSequence()
        .filterIsInstance<IrDeclarationWithName>()
        .filter { it is IrProperty }
        .map { "'${it.id}': ${it.value()}" }
        .joinToString(",", "({", "})")
}
