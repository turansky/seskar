package seskar.compiler.union.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer

internal class UnionTransformer(
    private val context: IrPluginContext,
) : IrElementTransformer<ValueMode?> {
    override fun visitClass(
        declaration: IrClass,
        data: ValueMode?,
    ): IrStatement {
        val unionBody = declaration.toJsUnionBody()
        if (unionBody != null) {
            declaration.annotations += JsName(context, declaration, unionBody)
        }

        return super.visitClass(declaration, data)
    }
}
