package seskar.compiler.serviceworker.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.name
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsFileName
import seskar.compiler.common.backend.JsName

internal class ServiceWorkerTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(declaration: IrProperty): IrStatement {
        if (!declaration.isServiceWorkerModule())
            return declaration

        val fileName = declaration.file.name.removeSuffix(".kt")
        declaration.file.annotations += JsFileName(context, "${fileName}__serviceworker__module")

        val jsName = sequenceOf(
            fileName,
            declaration.name.identifier,
        ).joinToString(
            separator = SERVICE_WORKER_MODULE_DELIMITER,
            prefix = SERVICE_WORKER_MODULE_DELIMITER,
            postfix = SERVICE_WORKER_MODULE_DELIMITER,
        )

        declaration.annotations += JsName(context, jsName)

        return super.visitProperty(declaration)
    }
}
