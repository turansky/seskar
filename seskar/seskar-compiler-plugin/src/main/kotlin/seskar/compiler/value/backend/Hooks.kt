package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.expressions.IrCall

internal fun isHookCall(
    expression: IrCall,
): Boolean {
    if (expression.dispatchReceiver != null)
        return false

    val function = expression.symbol.owner
    if (function.parent !is IrFile)
        return false

    return isHookName(function.name.identifier)
}

internal fun isHookName(
    name: String,
): Boolean =
    name.startsWith("use") &&
            name.length > 3 &&
            name[3].isUpperCase()
