package seskar.compiler.diagnostics

import org.jetbrains.kotlin.com.intellij.openapi.extensions.Extensions
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor

class SuppressorRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean = true

    override fun ExtensionStorage.registerExtensions(
        configuration: CompilerConfiguration,
    ) {
        @Suppress("DEPRECATION")
        val extensionArea = Extensions.getRootArea()

        if (!extensionArea.hasExtensionPoint(DiagnosticSuppressor.EP_NAME))
            return

        extensionArea
            .getExtensionPoint(DiagnosticSuppressor.EP_NAME)
            .registerExtension(RedundantDiagnosticSuppressor()) { /* do nothing */ }
    }
}
