package seskar.compiler.union.backend

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue
import org.jetbrains.kotlin.ir.util.companionObject
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_UNION = FqName("seskar.js.JsUnion")

private val JS_INT = FqName("seskar.js.JsInt")
private val JS_STRING = FqName("seskar.js.JsString")

private fun jsValue(i: Int): String = "$i"

private fun jsValue(s: String): String = "'$s'"

private val IrDeclarationWithName.id: String
    get() = name.identifier

private inline fun <reified T> IrConstructorCall.value(): T {
    val argument = getValueArgument(0) as IrConst<*>
    return argument.value as T
}

private fun IrDeclarationWithName.value(case: Case): String {
    val jsInt = getAnnotation(JS_INT)
    if (jsInt != null) {
        return jsValue(jsInt.value<Int>())
    }

    val jsString = getAnnotation(JS_STRING)
    if (jsString != null) {
        return jsValue(jsString.value<String>())
    }

    return jsValue(id.toCase(case))
}

internal fun IrClass.toJsUnionBody(): String? {
    if (!isExternal)
        return null

    if (kind != ClassKind.INTERFACE)
        return null

    val jsUnion = getAnnotation(JS_UNION)
        ?: return null

    val entry = jsUnion.getValueArgument(0) as IrGetEnumValue?
    val case = if (entry != null) {
        Case.valueOf(entry.symbol.owner.name.identifier)
    } else Case.ORIGINAL

    val companion = companionObject()
        ?: return null

    return companion.declarations.asSequence()
        .filterIsInstance<IrDeclarationWithName>()
        .filter { it is IrProperty || it is IrClass }
        .map { "'${it.id}': ${it.value(case)}" }
        .joinToString(",", "({", "})")
}
