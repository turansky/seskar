package seskar.compiler.serviceworker.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.FqName

internal const val SERVICE_WORKER_MODULE_DELIMITER = "$$"

private val SERVICE_WORKER_MODULE = FqName("web.serviceworker.ServiceWorkerModule")

internal fun IrProperty.isServiceWorkerModule(): Boolean =
    isTopLevel && getter?.returnType?.classFqName == SERVICE_WORKER_MODULE
