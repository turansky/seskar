package seskar.gradle.plugin

import seskar.gradle.plugin.Modules.ORIGINAL_MODULE_SUFFIX

class ReactLazyComponentFactory :
    LazyItemFactory {
    override fun create(
        export: String,
    ): LazyItem {
        val fileName = export
            .removePrefix("get_")
            .substringBefore("__react__component")

        val originalComponentPath = "./$fileName$ORIGINAL_MODULE_SUFFIX"

        val componentName = "$fileName\$\$__react__lazy__component"

        // language=javascript
        val body = """
        const $componentName = lazy(() => 
            import("$originalComponentPath")
                .then(module => module.${export}())
                .then(component => ({ default: component }))
        )
        
        export const $export = () => $componentName
        """.trimIndent()

        return LazyItem(
            imports = """import { lazy } from "react"""",
            body = body,
        )
    }
}
