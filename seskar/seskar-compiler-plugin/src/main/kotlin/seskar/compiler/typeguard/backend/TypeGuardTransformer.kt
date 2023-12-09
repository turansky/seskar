package seskar.compiler.typeguard.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsName
import seskar.compiler.common.backend.JsQualifier

private const val CONSTRUCTOR: String = "constructor"

private fun guard(
    name: String,
    data: TypeGuardData,
): String =
    // language=javascript
    """(class $name {
    static [Symbol.hasInstance](instance) {
        return instance && (typeof instance === 'object') && (instance.${data.property} === ${data.value})
    }
    }.prototype)"""

internal class TypeGuardTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        val data = declaration.typeGuardData()
        if (data != null) {
            declaration.file.annotations += JsQualifier(context, guard(declaration.name.asString(), data))
            declaration.annotations += JsName(context, CONSTRUCTOR)
        }

        return declaration
    }
}
