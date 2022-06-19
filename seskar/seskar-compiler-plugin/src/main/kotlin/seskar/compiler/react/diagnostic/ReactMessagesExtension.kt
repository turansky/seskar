package seskar.compiler.react.diagnostic

import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticFactoryToRendererMap

private val DIAGNOSTIC_FACTORY_TO_RENDERER by lazy {
    DiagnosticFactoryToRendererMap("seskar.react").apply {
        put(
            ReactErrors.NON_EXTERNAL_PROPS,
            "Non-external props are unsupported. Please add `external` modifier."
        )
    }
}

internal object ReactMessagesExtension : DefaultErrorMessages.Extension {
    override fun getMap(): DiagnosticFactoryToRendererMap =
        DIAGNOSTIC_FACTORY_TO_RENDERER
}
