package seskar.compiler.typeguard.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class TypeGuardTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        context

        return super.visitClass(declaration)
    }
}
