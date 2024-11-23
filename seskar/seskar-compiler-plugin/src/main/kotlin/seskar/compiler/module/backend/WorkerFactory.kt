package seskar.compiler.module.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.FqName

internal const val WORKER_DELIMITER = "$$"

private val WORKER_FACTORY = FqName("web.workers.WorkerFactory")

internal fun IrProperty.isWorkerFactory(): Boolean =
    isTopLevel && getter?.returnType?.classFqName == WORKER_FACTORY
