package seskar.compiler.common.extensions

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar

abstract class JsCompilerPluginRegistrar : CompilerPluginRegistrar() {
    final override val supportsK2: Boolean = true

    // Compatibility fix for Kotlin `2.3.0`
    // To be replaced with: override val pluginId
    fun getPluginId(): String =
        "io.github.turansky.seskar"
}
