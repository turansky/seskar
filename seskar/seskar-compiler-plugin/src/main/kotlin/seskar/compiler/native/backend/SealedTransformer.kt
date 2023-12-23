package seskar.compiler.native.backend

import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class SealedTransformer : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (declaration.isJsSealed())
            declaration.modality = Modality.SEALED

        return declaration
    }
}
