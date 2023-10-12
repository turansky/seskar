package seskar.compiler.key.backend

import org.jetbrains.kotlin.backend.wasm.ir2wasm.getSourceLocation
import org.jetbrains.kotlin.ir.IrFileEntry
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation

internal class KeyProvider(
    private val fileEntry: IrFileEntry,
) : IrElementTransformerVoid() {
    fun get(expression: IrCall): String {
        val location = expression.getSourceLocation(fileEntry)
        require(location is SourceLocation.Location) {
            "Invalid call location"
        }

        return "${location.line}_${location.column}"
    }
}
