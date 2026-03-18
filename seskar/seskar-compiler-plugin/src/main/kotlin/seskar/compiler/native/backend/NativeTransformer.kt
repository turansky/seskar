package seskar.compiler.native.backend

import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.backend.annotation

internal class NativeTransformer(
    private val context: SeskarPluginContext,
) : IrElementTransformerVoid() {
    override fun visitFunction(declaration: IrFunction): IrStatement {
        val nativeAnnotation = declaration.nativeAnnotation()
        if (nativeAnnotation != null) {
            declaration.annotations += annotation(context, nativeAnnotation)
        }

        return declaration
    }
}
