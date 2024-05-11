package seskar.compiler.common.extensions

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar

abstract class JsCompilerPluginRegistrar : CompilerPluginRegistrar() {
    final override val supportsK2: Boolean = true
}
