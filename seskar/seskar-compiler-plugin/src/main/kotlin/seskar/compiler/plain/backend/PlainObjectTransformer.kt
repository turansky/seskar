package seskar.compiler.plain.backend

import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class PlainObjectTransformer : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement =
        if (declaration.isPlainObject()) {
            super.visitClass(declaration)
        } else {
            declaration
        }

    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement {
        require(!declaration.isVar) {
            val className = declaration.parent.kotlinFqName.asString()
            val propertyName = declaration.name.identifier

            "Property $className.$propertyName is already var!"
        }

        declaration.isVar = true

        return declaration
    }
}
