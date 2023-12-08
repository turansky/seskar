package seskar.compiler.typeguard.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.file
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.JsName
import seskar.compiler.common.backend.JsQualifier

private const val GUARD_NAME: String = "__guard__"

private fun guard(
    name: String,
    data: TypeGuardData,
): String =
    // language=javascript
    """({
    $GUARD_NAME: {
        name: '$name',
        [Symbol.hasInstance](instance) {
            return instance && (typeof instance === 'object') && (instance.${data.property} === ${data.value})
        }
    }
    })"""

internal class TypeGuardTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        val data = declaration.typeGuardData()
        if (data != null) {
            declaration.file.annotations += JsQualifier(context, guard(declaration.name.asString(), data))
            declaration.annotations += JsName(context, GUARD_NAME)
        }

        return declaration
    }
}
