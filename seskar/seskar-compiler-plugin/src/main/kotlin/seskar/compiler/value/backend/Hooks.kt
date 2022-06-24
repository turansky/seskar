package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.name.Name

internal fun isHookCall(
    expression: IrCall,
): Boolean {
    if (expression.dispatchReceiver != null)
        return false

    val function = expression.symbol.owner
    if (function.parent !is IrFile)
        return false

    return isHookName(function.name)
}

internal fun isHookName(
    name: Name,
): Boolean {
    val id = name.identifierOrNullIfSpecial
        ?: return false

    return id.startsWith("use") &&
            id.length > 3 &&
            id[3].isUpperCase()
}
