package seskar.compiler.lazy.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsFileName
import seskar.compiler.common.backend.JsName

internal class LazyModuleTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(declaration: IrProperty): IrStatement {
        if (!declaration.isLazy())
            return declaration

        val componentName = declaration.name.identifier
        declaration.file.annotations += JsFileName(context, "${componentName}${LAZY_DELIMITER}lazy__module")
        declaration.annotations += JsName(context, componentName + "__react__component")

        return super.visitProperty(declaration)
    }
}
