package seskar.compiler.suspend.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.util.isSuspend
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.isReallyExternal

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

        return declaration
    }
}
