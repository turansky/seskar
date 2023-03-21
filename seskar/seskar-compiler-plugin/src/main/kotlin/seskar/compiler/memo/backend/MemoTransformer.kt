package seskar.compiler.memo.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.isSimpleProperty
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.displayname.backend.withDisplayName

private val FC = FqName("react.FC")

private val FC_FACTORIES = setOf(
    FqName("react.FC"),
    FqName("react.VFC"),
)

private val MEMO = CallableId(
    packageName = FqName("react"),
    className = null,
    callableName = Name.identifier("memo"),
)

internal class MemoTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement =
        propertyWithMemoization(declaration)
            ?: super.visitProperty(declaration)

    private fun propertyWithMemoization(
        declaration: IrProperty,
    ): IrStatement? {
        if (!declaration.isSimpleProperty)
            return null

        if (!declaration.isTopLevel)
            return null

        val backingField = declaration.backingField
            ?: return null

        val typeName = backingField.type.classFqName
            ?: return null

        if (typeName != FC)
            return null

        val initializer = backingField.initializer
            ?: return null

        val call = initializer.expression as? IrCall
            ?: return null

        val factory = if (memoizationRequired(call)) {
            memo(call)
        } else call

        if (factory == call)
            return null

        initializer.expression = withDisplayName(
            context = context,
            componentFactory = factory,
            displayName = declaration.name.identifier,
        )

        return declaration
    }

    private fun memoizationRequired(
        call: IrCall,
    ): Boolean {
        val functionName = call.symbol.owner.fqNameWhenAvailable
            ?: return false

        return functionName in FC_FACTORIES
    }

    private fun memo(
        componentFactory: IrCall,
    ): IrExpression {
        val memo = context.referenceFunctions(MEMO).single()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = componentFactory.startOffset,
            endOffset = componentFactory.endOffset,
            symbol = memo,
        )

        call.putValueArgument(0, componentFactory)

        return call
    }
}
