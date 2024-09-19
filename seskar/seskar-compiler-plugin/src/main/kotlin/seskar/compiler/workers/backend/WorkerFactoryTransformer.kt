package seskar.compiler.workers.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.name
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsFileName
import seskar.compiler.common.backend.JsName

internal class WorkerFactoryTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(declaration: IrProperty): IrStatement {
        if (!declaration.isWorkerFactory())
            return declaration

        val fileName = declaration.file.name.removeSuffix(".kt")
        declaration.file.annotations += JsFileName(context, "${fileName}__worker__factory")

        val jsName = sequenceOf(
            fileName,
            declaration.name.identifier,
            "worker",
        ).joinToString(
            separator = WORKER_DELIMITER,
            prefix = WORKER_DELIMITER,
            postfix = WORKER_DELIMITER,
        )

        declaration.annotations += JsName(context, jsName)

        return super.visitProperty(declaration)
    }
}
