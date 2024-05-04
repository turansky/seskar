package seskar.compiler.react.memo.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.isSimpleProperty
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.irCall
import seskar.compiler.react.displayname.backend.withDisplayName

private val FC = FqName("react.FC")
private val FORWARD_REF_EXOTIC_COMPONENT = FqName("react.ForwardRefExoticComponent")

private val CONTEXT_TYPES = setOf(
    FqName("react.Context"),
    FqName("react.RequiredContext"),
)

private val FC_FACTORIES = setOf(
    FqName("react.FC"),
)

private val FORWARD_REF_FACTORIES = setOf(
    FqName("react.ForwardRef"),
)

private val CONTEXT_FACTORIES = setOf(
    FqName("react.createContext"),
    FqName("react.createRequiredContext"),
)

private val MEMO = CallableId(
    packageName = FqName("react"),
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

        if (typeName != FC && typeName != FORWARD_REF_EXOTIC_COMPONENT && typeName !in CONTEXT_TYPES)
            return null

        val initializer = backingField.initializer
            ?: return null

        val call = initializer.expression as? IrCall
            ?: return null

        val factory = if (memoizationRequired(call)) {
            memo(call)
        } else call

        val expression = if (displayNameRequired(call)) {
            withDisplayName(
                context = context,
                componentFactory = factory,
                displayName = declaration.name.identifier,
            )
        } else factory

        if (expression == call)
            return null

        initializer.expression = expression
        return declaration
    }

    private fun memoizationRequired(
        call: IrCall,
    ): Boolean {
        val functionName = call.symbol.owner.fqNameWhenAvailable
            ?: return false

        return functionName in FC_FACTORIES
                || functionName in FORWARD_REF_FACTORIES
    }

    private fun displayNameRequired(
        call: IrCall,
    ): Boolean {
        val functionName = call.symbol.owner.fqNameWhenAvailable
            ?: return false

        return functionName in FC_FACTORIES
                || functionName in FORWARD_REF_FACTORIES
                || functionName in CONTEXT_FACTORIES
    }

    private fun memo(
        componentFactory: IrCall,
    ): IrExpression {
        val memo = context.referenceFunctions(MEMO).single()

        val call = irCall(memo)
        call.putValueArgument(0, componentFactory)

        return call
    }
}
