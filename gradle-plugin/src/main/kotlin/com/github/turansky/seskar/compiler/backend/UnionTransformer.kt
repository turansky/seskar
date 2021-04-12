package com.github.turansky.seskar.compiler.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class UnionTransformer(
    private val context: IrPluginContext
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (declaration.isExternal && declaration.kind == ClassKind.ENUM_CLASS) {
            val name = "SeskarUnion" + declaration.name.identifier

            declaration.annotations += JsName(context, declaration, name)
        }

        return super.visitClass(declaration)
    }
}

