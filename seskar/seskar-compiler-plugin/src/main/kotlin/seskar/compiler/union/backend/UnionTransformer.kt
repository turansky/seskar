package seskar.compiler.union.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.ir.addDispatchReceiver
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.declarations.addGetter
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.util.isTopLevelDeclaration
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer

internal class UnionTransformer(
    private val context: IrPluginContext,
) : IrElementTransformer<ValueMode?> {
    override fun visitClass(
        declaration: IrClass,
        data: ValueMode?,
    ): IrStatement {
        val value = declaration.value()

        if (value != null && declaration.isTopLevelDeclaration && declaration.kind == ClassKind.OBJECT) {
            declaration.annotations += JsName(context, declaration, value.toJsName())
            return declaration
        }

        val mode = when {
            declaration.isJsUnion()
            -> ValueMode.ROOT

            data == ValueMode.ROOT && declaration.isCompanion
            -> ValueMode.COMPANION

            else -> null
        }

        if (mode == ValueMode.ROOT) {
            declaration.annotations += JsName(context, declaration, "0")
        }

        return super.visitClass(declaration, mode)
    }

    override fun visitProperty(
        declaration: IrProperty,
        data: ValueMode?,
    ): IrStatement {
        val value = declaration.value(useDefaultValue = (data == ValueMode.COMPANION))

        if (value != null) {
            addPropertyGetter(declaration, value)
        }

        return declaration
    }

    private fun addPropertyGetter(
        declaration: IrProperty,
        value: Value,
    ) {
        val getter = declaration.addGetter {
            isInline = true
            returnType = context.irBuiltIns.stringType
        }

        getter.addDispatchReceiver {
            type = context.irBuiltIns.nothingNType
        }

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
