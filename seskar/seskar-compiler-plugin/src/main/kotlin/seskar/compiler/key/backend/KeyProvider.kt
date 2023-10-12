package seskar.compiler.key.backend

import org.jetbrains.kotlin.backend.wasm.ir2wasm.getSourceLocation
import org.jetbrains.kotlin.ir.IrFileEntry
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.wasm.ir.source.location.SourceLocation

@JvmInline
internal value class KeyProvider(
    private val fileEntry: IrFileEntry,
) {
    fun get(expression: IrCall): String {
        val location = expression.getSourceLocation(fileEntry)
        require(location is SourceLocation.Location) {
            "Invalid call location"
        }

        return "${location.line}_${location.column}"
    }
}
