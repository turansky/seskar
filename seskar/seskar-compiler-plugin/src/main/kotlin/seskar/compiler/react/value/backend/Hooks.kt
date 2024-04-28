package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.Name

internal fun isHookCall(
    expression: IrCall,
): Boolean {
    if (expression.dispatchReceiver != null)
        return false

    val function = expression.symbol.owner

    // are hooks toplevel only? ( potentially namespace / object )
    if (!function.isTopLevel)
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
