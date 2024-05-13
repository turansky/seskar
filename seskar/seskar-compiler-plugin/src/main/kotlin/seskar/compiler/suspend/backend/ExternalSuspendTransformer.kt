package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody
import org.jetbrains.kotlin.ir.expressions.impl.IrExpressionBodyImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.util.isSuspend
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.irCall
import seskar.compiler.common.backend.irGet
import seskar.compiler.common.backend.isReallyExternal

private val AWAIT_PROMISE_LIKE = CallableId(
    packageName = FqName("js.promise.internal"),
    callableName = Name.identifier("awaitPromiseLike"),
)

private val AWAIT_OPTIONAL_PROMISE_LIKE = CallableId(
    packageName = FqName("js.promise.internal"),
    callableName = Name.identifier("awaitOptionalPromiseLike"),
)

private val UNDEFINED = CallableId(
    packageName = FqName("kotlin.js"),
    callableName = Name.identifier("undefined"),
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

        val useTransform = if (declaration.isTopLevel) {
            declaration.isExternal
        } else {
            !declaration.isInline
        }

        if (!useTransform) {
            return declaration
        }

        patchFunction(declaration)
        return declaration
    }

    private fun patchFunction(
        declaration: IrFunction,
    ) {
        for (parameter in declaration.valueParameters) {
            if (parameter.defaultValue != null) {
                parameter.defaultValue = undefined()
            }
        }

        addFunctionBody(declaration)
    }

    private fun addFunctionBody(
        declaration: IrFunction,
    ) {
        val suspendCall = suspendCall(declaration)
        val statement = if (declaration.returnType != context.irBuiltIns.unitType) {
            IrReturnImpl(
                startOffset = UNDEFINED_OFFSET,
                endOffset = UNDEFINED_OFFSET,
                type = declaration.returnType,
                returnTargetSymbol = declaration.symbol,
                value = suspendCall,
            )
        } else {
            suspendCall
        }

        declaration.isInline = true
        declaration.isExternal = false
        declaration.body = context.irFactory.createBlockBody(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            statements = listOf(statement),
        )
    }

    private fun suspendCall(
        declaration: IrFunction,
    ): IrExpression {
        val asyncFunctionSymbol = findAsyncFunctionSymbol(context, declaration)

        val promiseCall = irCall(asyncFunctionSymbol)

        val dispatchReceiverParameter = declaration.dispatchReceiverParameter
        if (dispatchReceiverParameter != null) {
            promiseCall.dispatchReceiver = irGet(dispatchReceiverParameter)
        }

        declaration.valueParameters.forEachIndexed { index, parameter ->
            promiseCall.putValueArgument(index, irGet(parameter))
        }

        return await(promiseCall, declaration.getAsyncOptions())
    }

    private fun await(
        promiseCall: IrCall,
        options: AsyncOptions,
    ): IrExpression {
        val awaitFunctionId = if (options.optional) AWAIT_OPTIONAL_PROMISE_LIKE else AWAIT_PROMISE_LIKE
        val await = context.referenceFunctions(awaitFunctionId).single()

        val call = irCall(await)
        call.putValueArgument(0, promiseCall)
        return call
    }

    private fun undefined(): IrExpressionBody {
        val property = context.referenceProperties(UNDEFINED).single()
        return IrExpressionBodyImpl(irGet(property))
    }
}
