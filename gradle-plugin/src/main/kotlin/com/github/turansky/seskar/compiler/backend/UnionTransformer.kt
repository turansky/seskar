package com.github.turansky.seskar.compiler.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class UnionTransformer(
    private val context: IrPluginContext
) : IrElementTransformerVoid() {
    override fun visitCall(expression: IrCall): IrExpression {
        context

        return super.visitCall(expression)
    }
}

