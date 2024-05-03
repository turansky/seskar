package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.util.functions
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.Name

internal fun findAsyncFunctionSymbol(
    context: IrPluginContext,
    function: IrFunction,
): IrSimpleFunctionSymbol {
    val parent = function.parent
    val asyncName = Name.identifier(function.name.identifier + "Async")

    return when (parent) {
        is IrFile -> {
            val functionId = CallableId(
                packageName = parent.packageFqName,
                callableName = asyncName,
            )

            context.referenceFunctions(functionId).first {
                val owner = it.owner
                owner.isExternal && !owner.isSuspend
            }
        }

        is IrClass -> {
            parent.functions
                .filter { !it.isSuspend }
                .first { it.name == asyncName }
                .symbol
        }

        else -> TODO("Unable to find async function for parent ${parent::class.simpleName}")
    }
}
