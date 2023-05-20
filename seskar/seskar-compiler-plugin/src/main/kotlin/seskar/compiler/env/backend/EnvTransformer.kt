package seskar.compiler.env.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.union.backend.JsName

internal class EnvTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(declaration: IrProperty): IrStatement {
        val variableName = declaration.toEnvVariableName()
        if (variableName != null) {
            declaration.annotations += JsName(context, declaration, variableName)
        }

        return super.visitProperty(declaration)
    }
}
