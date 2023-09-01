package seskar.compiler.key.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val ELEMENT_BUILDER = FqName("react.ElementBuilder")

private val SET_DEFAULT_KEY = CallableId(
    packageName = FqName("react"),
    className = null,
    callableName = Name.identifier("setDefaultKey"),
)

internal class DefaultKeyTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    // use WeakMap instead?
    private var keyIndex = 0

    override fun visitFile(declaration: IrFile): IrFile {
        keyIndex = 0

        return super.visitFile(declaration)
    }

    override fun visitCall(expression: IrCall): IrExpression {
        val keyCall = keyCall(expression)
        val originalCall = super.visitCall(expression)

        if (keyCall == null)
            return originalCall

        return IrCompositeImpl(
            startOffset = expression.startOffset,
            endOffset = expression.endOffset,
            type = expression.type,
            origin = expression.origin,
            statements = listOf(
                keyCall,
                originalCall,
            )
        )
    }

    private fun keyCall(expression: IrCall): IrCall? {
        val dispatchReceiver = expression.dispatchReceiver
            ?: return null

        if (!expression.symbol.owner.hasAnnotation(ELEMENT_BUILDER))
            return null

        val setDefaultKey = context.referenceFunctions(SET_DEFAULT_KEY).single()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = expression.startOffset,
            endOffset = expression.endOffset,
            symbol = setDefaultKey,
        )

        val defaultKey = IrConstImpl.string(
            startOffset = expression.startOffset,
            endOffset = expression.endOffset,
            type = context.symbols.string.defaultType,
            value = "@rdk/${++keyIndex}/${expression.hashCode()}",
        )

        call.putValueArgument(0, dispatchReceiver)
        call.putValueArgument(1, defaultKey)

        return call
    }
}
