package seskar.compiler.specialname.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsName

internal class SpecialNameTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        val specialName = declaration.specialName()
        if (specialName != null) {
            declaration.annotations += JsName(context, specialName)
        }

        return super.visitClass(declaration)
    }

    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement {
        val specialName = declaration.specialName()
        if (specialName != null) {
            declaration.annotations += JsName(context, specialName)
        }

        return declaration
    }
}
