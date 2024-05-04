package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.util.isSuspend
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.isReallyExternal

private val AWAIT = CallableId(
    packageName = FqName("js.promise"),
    callableName = Name.identifier("await"),
)

internal class ExternalSuspendTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(
        declaration: IrClass,
    ): IrStatement {
        if (!isReallyExternal(declaration))
            return declaration

        return super.visitClass(declaration)
    }

    override fun visitFunction(
        declaration: IrFunction,
    ): IrStatement {
        if (!declaration.isSuspend) {
            return declaration
        }

        if (declaration.isTopLevel && !declaration.isExternal) {
            return declaration
        }

        addFunctionBody(declaration)

        return declaration
    }

    private fun addFunctionBody(
        declaration: IrFunction,
    ) {
        declaration.isInline = true
        declaration.body = context.irFactory.createBlockBody(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            statements = listOf(
                IrReturnImpl(
                    startOffset = UNDEFINED_OFFSET,
                    endOffset = UNDEFINED_OFFSET,
                    type = declaration.returnType,
                    returnTargetSymbol = declaration.symbol,
                    value = suspendCall(declaration),
                )
            )
        )
    }

    private fun suspendCall(
        declaration: IrFunction,
    ): IrExpression {
        val asyncFunctionSymbol = findAsyncFunctionSymbol(context, declaration)

        val promiseCall = IrCallImpl.fromSymbolOwner(
            startOffset = declaration.startOffset,
            endOffset = declaration.endOffset,
            symbol = asyncFunctionSymbol,
        )

        val dispatchReceiverParameter = declaration.dispatchReceiverParameter
        if (dispatchReceiverParameter != null) {
            promiseCall.dispatchReceiver = IrGetValueImpl(
                startOffset = UNDEFINED_OFFSET,
                endOffset = UNDEFINED_OFFSET,
                symbol = dispatchReceiverParameter.symbol,
            )
        }

        return await(promiseCall)
    }

    private fun await(
        promiseCall: IrCall,
    ): IrExpression {
        val await = context.referenceFunctions(AWAIT).single()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            symbol = await,
        )

        call.extensionReceiver = promiseCall

        return call
    }
}
