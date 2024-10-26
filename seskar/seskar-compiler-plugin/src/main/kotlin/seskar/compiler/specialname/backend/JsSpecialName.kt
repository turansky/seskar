package seskar.compiler.specialname.backend

import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.value

private val JS_SPECIAL_NAME = FqName("seskar.js.JsSpecialName")

internal fun IrDeclarationBase.specialName(): String? {
    val name = getAnnotation(JS_SPECIAL_NAME)
        ?: return null

    return name.value<String>()
}
