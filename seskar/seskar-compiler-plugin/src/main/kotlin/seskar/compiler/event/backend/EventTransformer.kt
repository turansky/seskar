package seskar.compiler.event.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.isReallyExternal

internal class EventTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(
        declaration: IrClass,
    ): IrStatement {
        if (!isReallyExternal(declaration))
            return declaration

        return super.visitClass(declaration)
    }

    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement {
        val type = declaration.eventType()
            ?: return declaration

        // TODO: implement

        return declaration
    }
}
