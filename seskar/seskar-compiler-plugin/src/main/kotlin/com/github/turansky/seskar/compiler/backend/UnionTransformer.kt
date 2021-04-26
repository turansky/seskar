package com.github.turansky.seskar.compiler.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.FqName

private val JS_UNION = FqName("seskar.js.JsUnion")

private val IrClass.isJsUnion: Boolean
    get() = isExternal && kind == ClassKind.ENUM_CLASS && hasAnnotation(JS_UNION)

internal class UnionTransformer(
    private val context: IrPluginContext
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (declaration.isJsUnion) {
            val unionBody = declaration.declarations.asSequence()
                .filterIsInstance<IrEnumEntry>()
                .map { it.name.identifier }
                .map { "$it: '$it'" }
                .joinToString(",", "{", "}")

            declaration.annotations += JsName(context, declaration, unionBody)
        }

        return super.visitClass(declaration)
    }
}

