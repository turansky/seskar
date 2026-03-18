package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.SeskarPluginContext

private val MEMBER_TO_STRING = CallableId(
    packageName = FqName("kotlin"),
    className = FqName("Any"),
    callableName = Name.identifier("toString"),
)

private val EXTENSION_TO_STRING = CallableId(
    packageName = FqName("kotlin"),
    callableName = Name.identifier("toString"),
)

internal class Symbols(
    private val context: SeskarPluginContext,
) {
    val extensionToString: IrSimpleFunctionSymbol by lazy {
        context.findFunction(EXTENSION_TO_STRING)
    }

    val memberToString: IrSimpleFunctionSymbol by lazy {
        context.findFunction(MEMBER_TO_STRING)
    }
}
