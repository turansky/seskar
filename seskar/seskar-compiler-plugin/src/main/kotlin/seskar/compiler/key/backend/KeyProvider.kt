package seskar.compiler.key.backend

import org.jetbrains.kotlin.ir.IrFileEntry
import org.jetbrains.kotlin.ir.expressions.IrCall

@JvmInline
internal value class KeyProvider(
    private val fileEntry: IrFileEntry,
) {
    fun get(expression: IrCall): String {
        val startLine = fileEntry.getLineNumber(expression.startOffset)
        val startColumn = fileEntry.getColumnNumber(expression.startOffset)

        require(startLine >= 0 && startColumn >= 0) {
            "Invalid call location"
        }

        return "${startLine}_${startColumn}"
    }
}
