package seskar.compiler.lazy.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.name
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.JsFileName
import seskar.compiler.common.backend.JsName

private val TYPE_MAP = mapOf(
    FqName("js.lazy.LazyFunction") to "lazy__function",
    FqName("react.FC") to "react__component",
)

internal class LazyModuleTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(declaration: IrProperty): IrStatement {
        if (!declaration.isLazy())
            return declaration

        val fileName = declaration.file.name.removeSuffix(".kt")
        declaration.file.annotations += JsFileName(context, "${fileName}__lazy__module")

        val propertyType = declaration.getter!!.returnType.classFqName
        val type = TYPE_MAP[propertyType]
            ?: error("Unsupported lazy property type: $propertyType")

        val jsName = sequenceOf(
            fileName,
            declaration.name.identifier,
            type,
        ).joinToString(
            separator = LAZY_DELIMITER,
            prefix = LAZY_DELIMITER,
            postfix = LAZY_DELIMITER,
        )

        declaration.annotations += JsName(context, jsName)

        return super.visitProperty(declaration)
    }
}
