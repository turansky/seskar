package seskar.compiler.primitive.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val HAS_TYPE = CallableId(
    packageName = FqName("seskar.js.internal"),
    className = null,
    callableName = Name.identifier("hasType"),
)

internal class PrimitiveTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid()
