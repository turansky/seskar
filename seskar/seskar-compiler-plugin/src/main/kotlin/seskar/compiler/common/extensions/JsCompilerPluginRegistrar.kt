package seskar.compiler.common.extensions

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar

abstract class JsCompilerPluginRegistrar : CompilerPluginRegistrar() {
    final override val supportsK2: Boolean = true

    // Compatibility fix for Kotlin `2.3.0`
    fun getPluginId(): String =
        "io.github.turansky.seskar"
}
