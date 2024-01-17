package seskar.compiler.plain.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.declarations.addSetter
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class PlainObjectTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(declaration: IrClass): IrStatement {
        if (!declaration.isPlainObject())
            return declaration

        val className = declaration.kotlinFqName.asString()

        declaration.declarations.asSequence()
            .filterIsInstance<IrProperty>()
            .forEach { property ->
                require(!property.isVar) {
                    val propertyName = property.name.identifier

                    "Property $className.$propertyName is already var!"
                }

                property.isVar = true
                property.addSetter {
                    returnType = context.irBuiltIns.unitType
                }
            }

        return declaration
    }
}
