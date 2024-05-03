package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.util.functions
import org.jetbrains.kotlin.name.Name

internal fun findAsyncFunction(
    function: IrFunction,
): IrFunction {
    val klass = function.parent as? IrClass
        ?: return function

    val name = function.name
    val asyncName = Name.identifier(name.identifier + "Async")

    return klass.functions
        .first { it.name == asyncName }
}
