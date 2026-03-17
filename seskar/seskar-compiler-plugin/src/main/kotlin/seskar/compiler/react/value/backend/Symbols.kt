package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

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
    private val context: IrPluginContext,
) {
    val extensionToString: IrSimpleFunctionSymbol by lazy {
        context.finderForBuiltins()
            .findFunctions(EXTENSION_TO_STRING)
            .single()
    }

    val memberToString: IrSimpleFunctionSymbol by lazy {
        context.finderForBuiltins()
            .findFunctions(MEMBER_TO_STRING)
            .single()
    }
}
