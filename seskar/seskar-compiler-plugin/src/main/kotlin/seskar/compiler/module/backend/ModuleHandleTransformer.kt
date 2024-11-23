package seskar.compiler.module.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsFileName
import seskar.compiler.common.backend.JsName

internal class ModuleHandleTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(declaration: IrProperty): IrStatement {
        if (!declaration.isModuleHandle())
            return declaration

        val moduleId = declaration.fqNameWhenAvailable!!.asString()
        val fileName = moduleId.replace('.', '_') + "__module__handle"
        declaration.file.annotations += JsFileName(context, fileName)

        val jsName = MODULE_HANDLE_DELIMITER + fileName + MODULE_HANDLE_DELIMITER
        declaration.annotations += JsName(context, jsName)

        return super.visitProperty(declaration)
    }
}
