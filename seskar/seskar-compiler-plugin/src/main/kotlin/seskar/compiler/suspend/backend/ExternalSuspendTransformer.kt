package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.types.classOrNull
import org.jetbrains.kotlin.ir.types.isSubtypeOfClass
import org.jetbrains.kotlin.ir.util.isInterface
import org.jetbrains.kotlin.ir.util.isSuspend
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.*

private val UNDEFINED = CallableId(
    packageName = FqName("kotlin.js"),
    callableName = Name.identifier("undefined"),
)

private val AWAIT_PROMISE_LIKE = CallableId(
    packageName = FqName("js.promise.internal"),
    callableName = Name.identifier("awaitPromiseLike"),
)

private val AWAIT_OPTIONAL_PROMISE_LIKE = CallableId(
    packageName = FqName("js.promise.internal"),
    callableName = Name.identifier("awaitOptionalPromiseLike"),
)

private val ABORTABLE = ClassId(
    FqName("web.abort"),
    Name.identifier("Abortable"),
)

private val ABORT_CONTROLLER = ClassId(
    FqName("web.abort"),
    Name.identifier("AbortController"),
)

private val PATCH_ABORT_OPTIONS = CallableId(
    packageName = FqName("web.abort.internal"),
    callableName = Name.identifier("patchAbortOptions"),
)

private val AWAIT_PROMISE_LIKE_WITH_CANCELLATION = CallableId(
    packageName = FqName("web.abort.internal"),
    callableName = Name.identifier("awaitPromiseLike"),
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
        val controller = abortController(declaration)
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
            statements = listOfNotNull(controller, statement),
        )
    }

    private fun abortController(
        declaration: IrFunction,
    ): IrVariable? {
        if (!hasAbortableOptions(declaration))
            return null

        val constructor = context.referenceConstructors(ABORT_CONTROLLER)
            .first { it.owner.valueParameters.isEmpty() }

        return irVariable(
            Name.identifier("controller"),
            constructor.owner.returnType,
        ).also {
            it.initializer = irConstructorCall(constructor)
        }
    }

    private fun hasAbortableOptions(
        declaration: IrFunction,
    ): Boolean {
        val options = declaration.valueParameters.lastOrNull()
            ?: return false

        val classSymbol = options.type.classOrNull
            ?: return false

        val klass = classSymbol.owner
        if (!klass.isInterface)
            return false

        if (!klass.isExternal)
            return false

        val abortable = context.referenceClass(ABORTABLE)
            ?: return false

        return classSymbol.isSubtypeOfClass(abortable)
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

        val asyncOptions = declaration.getAsyncOptions()
        return await(promiseCall, asyncOptions)
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

    private fun awaitWithCancellation(
        promiseCall: IrCall,
    ): IrExpression {
        val await = context.referenceFunctions(AWAIT_PROMISE_LIKE_WITH_CANCELLATION).single()

        val call = irCall(await)
        call.putValueArgument(0, promiseCall)
        return call
    }

    private fun undefined(): IrExpressionBody {
        val property = context.referenceProperties(UNDEFINED).single()
        return context.irFactory.createExpressionBody(irGet(property))
    }
}
