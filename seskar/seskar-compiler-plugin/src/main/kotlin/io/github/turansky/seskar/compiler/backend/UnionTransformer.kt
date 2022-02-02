package io.github.turansky.seskar.compiler.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class UnionTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        val unionBody = declaration.toJsUnionBody()
        if (unionBody != null) {
            declaration.annotations += JsName(context, declaration, unionBody)
        }

        return super.visitClass(declaration)
    }
}

