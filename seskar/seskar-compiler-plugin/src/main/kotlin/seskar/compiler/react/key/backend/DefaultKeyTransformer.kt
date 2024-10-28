package seskar.compiler.react.key.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.irCall
import seskar.compiler.common.backend.stringConst

private val ELEMENT_BUILDER = FqName("react.ElementBuilder")

private val SET_DEFAULT_KEY = CallableId(
    packageName = FqName("react"),
    callableName = Name.identifier("setDefaultKey"),
)

internal class DefaultKeyTransformer(
    private val context: IrPluginContext,
) : IrElementTransformer<KeyProvider?> {
    override fun visitFile(
        declaration: IrFile,
        data: KeyProvider?,
    ): IrFile =
        super.visitFile(
            declaration = declaration,
            data = KeyProvider(declaration.fileEntry),
        )

    override fun visitCall(
        expression: IrCall,
        data: KeyProvider?,
    ): IrElement {
        val originalCall = super.visitCall(expression, data)
        data ?: return originalCall

        val keyCall = keyCall(expression, data)
            ?: return originalCall

        // TODO: check how to avoid
        originalCall as IrStatement

        val composite = IrCompositeImpl(
            startOffset = expression.startOffset,
            endOffset = expression.endOffset,
            type = expression.type,
            origin = expression.origin,
        )

        composite.statements.add(keyCall)
        composite.statements.add(originalCall)

        return composite
    }

    private fun keyCall(
        expression: IrCall,
        keyProvider: KeyProvider,
    ): IrCall? {
        val dispatchReceiver = expression.dispatchReceiver
            ?: return null

        if (!expression.symbol.owner.hasAnnotation(ELEMENT_BUILDER))
            return null

        val setDefaultKey = context.referenceFunctions(SET_DEFAULT_KEY).single()

        val key = keyProvider.get(expression)

        val call = irCall(setDefaultKey)

        call.putValueArgument(0, dispatchReceiver)
        call.putValueArgument(1, stringConst(context, "@rdk/$key"))

        return call
    }
}
