package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.functions
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.Name

internal fun findAsyncFunctionSymbol(
    context: IrPluginContext,
    function: IrFunction,
): IrSimpleFunctionSymbol {
    val parent = function.parent
    val asyncName = Name.identifier(function.name.identifier + "Async")
    val parameterCount = function.valueParameters.size

    return when (parent) {
        is IrFile -> {
            val functionId = CallableId(
                packageName = parent.packageFqName,
                callableName = asyncName,
            )

            context.referenceFunctions(functionId)
                .asSequence()
                .filter { it.owner.isExternal }
                .filter { !it.owner.isSuspend }
                .filter { it.owner.valueParameters.size >= parameterCount }
                .sortedBy { it.owner.valueParameters.size }
                .firstOrNull()
                ?: error("Unable to find async companion for function ${function.fqNameWhenAvailable}")
        }

        is IrClass -> {
            val asyncFunction = parent.functions
                .filter { !it.isSuspend }
                .filter { it.name == asyncName }
                .filter { it.valueParameters.size >= parameterCount }
                .sortedBy { it.valueParameters.size }
                .firstOrNull()
                ?: error("Unable to find async companion for function ${function.fqNameWhenAvailable}")

            asyncFunction.symbol
        }

        else -> error("Unable to find async function for parent ${parent::class.simpleName}")
    }
}
