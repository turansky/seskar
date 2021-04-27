package com.github.turansky.seskar.compiler.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstKind
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.FqName

private val JS_UNION = FqName("seskar.js.JsUnion")

private val JS_STRING = FqName("seskar.js.JsString")

private val IrClass.isJsUnion: Boolean
    get() = isExternal && kind == ClassKind.ENUM_CLASS && hasAnnotation(JS_UNION)

private val IrEnumEntry.id: String
    get() = name.identifier

private val IrEnumEntry.value: String
    get() {
        val jsValue = getAnnotation(JS_STRING)
            ?: return jsValue(id)

        val argument = jsValue.getValueArgument(0) as IrConst<*>
        val value = argument.value
        return when (argument.kind) {
            IrConstKind.Int -> jsValue(value as Int)
            IrConstKind.String -> jsValue(argument.value as String)
            else -> "null"
        }
    }

private fun jsValue(s: String): String = "'$s'"

private fun jsValue(i: Int): String = "$i"

internal class UnionTransformer(
    private val context: IrPluginContext
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (declaration.isJsUnion) {
            val unionBody = declaration.declarations.asSequence()
                .filterIsInstance<IrEnumEntry>()
                .map { "${it.id}: ${it.value}" }
                .joinToString(",", "{", "}")

            declaration.annotations += JsName(context, declaration, unionBody)
        }

        return super.visitClass(declaration)
    }
}

