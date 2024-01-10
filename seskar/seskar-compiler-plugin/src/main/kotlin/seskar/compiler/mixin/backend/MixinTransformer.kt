package seskar.compiler.mixin.backend

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class MixinTransformer : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (declaration.isExternal && declaration.kind == ClassKind.CLASS) {
            val mixins = declaration.superTypes.filter { it.isMixin() }
            if (mixins.isNotEmpty()) {
                declaration.superTypes -= mixins
            }
        }

        return declaration
    }
}
