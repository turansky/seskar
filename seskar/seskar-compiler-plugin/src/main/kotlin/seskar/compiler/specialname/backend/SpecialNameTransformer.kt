package seskar.compiler.specialname.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.util.isTopLevelDeclaration
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.union.backend.*

internal class SpecialNameTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(
        declaration: IrClass,
    ): IrStatement {
        if (!declaration.isExternal)
            return declaration

        val value = declaration.value()

        if (value != null && declaration.isTopLevelDeclaration && declaration.kind == ClassKind.OBJECT) {
            declaration.annotations += JsName(context, declaration, value.toJsName())
            return declaration
        }

        if (declaration.isJsVirtual()) {
            declaration.annotations += JsName(context, declaration, "0")
        }

        return super.visitClass(declaration)
    }

    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement {
        val value = declaration.value()

        if (value != null) {
            addPropertyGetter(declaration, value)
        }

        return declaration
    }

    private fun addPropertyGetter(
        declaration: IrProperty,
        value: Value,
    ) {
        val getter = declaration.getter
            ?: error("No default getter!")

        getter.isInline = true
        getter.body = context.irFactory.createBlockBody(
            startOffset = declaration.startOffset,
            endOffset = declaration.endOffset,
            statements = listOf(
                IrReturnImpl(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.nothingNType,
                    returnTargetSymbol = getter.symbol,
                    value = valueConstant(declaration, value),
                )
            )
        )
    }

    private fun valueConstant(
        declaration: IrDeclarationWithName,
        value: Value,
    ): IrExpression {
        return when (value) {
            is IntValue ->
                IrConstImpl.int(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.intType,
                    value = value.value,
                )

            is StringValue ->
                IrConstImpl.string(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.stringType,
                    value = value.value,
                )
        }
    }
}
