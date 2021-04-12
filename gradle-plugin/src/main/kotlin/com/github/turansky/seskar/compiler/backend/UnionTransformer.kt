package com.github.turansky.seskar.compiler.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.FqName

private val JS_NAME = FqName("kotlin.js.JsName")

internal class UnionTransformer(
    private val context: IrPluginContext
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (declaration.isExternal && declaration.kind == ClassKind.ENUM_CLASS) {
            val jsNameType = context.referenceClass(JS_NAME)!!.defaultType
            val jsName = context.referenceConstructors(JS_NAME).single()
            val annotation = IrConstructorCallImpl(
                startOffset = declaration.startOffset,
                endOffset = declaration.endOffset,
                type = jsNameType,
                symbol = jsName,
                typeArgumentsCount = 0,
                constructorTypeArgumentsCount = 0,
                valueArgumentsCount = 1,
                origin = null,
            )

            val name = IrConstImpl.string(
                startOffset = declaration.startOffset,
                endOffset = declaration.endOffset,
                type = context.irBuiltIns.stringType,
                value = "Seskar" + declaration.name.identifier
            )

            annotation.putValueArgument(0, name)

            declaration.annotations += annotation
        }

        return super.visitClass(declaration)
    }
}

