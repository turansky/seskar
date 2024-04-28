package seskar.compiler.react.value.diagnostic

import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticFactoryToRendererMap

private val DIAGNOSTIC_FACTORY_TO_RENDERER by lazy {
    DiagnosticFactoryToRendererMap("seskar.value").apply {
        put(
            ValueErrors.NULLABLE_JS_VALUE,
            "Nullable JS value is unsupported"
        )
        put(
            ValueErrors.INVALID_JS_VALUE_TYPE,
            "Invalid JS value type. Currently supported value types: String, Int, Double, Long"
        )
    }
}

internal object ValueMessagesExtension : DefaultErrorMessages.Extension {
    override fun getMap(): DiagnosticFactoryToRendererMap =
        DIAGNOSTIC_FACTORY_TO_RENDERER
}
