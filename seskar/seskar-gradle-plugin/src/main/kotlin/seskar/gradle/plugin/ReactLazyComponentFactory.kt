package seskar.gradle.plugin

// language=javascript
private val IMPORTS = """
import { lazy } from "react"
""".trimIndent()

class ReactLazyComponentFactory :
    LazyItemFactory {
    override fun create(
        data: LazyItemData,
    ): LazyItem {
        val componentName = "${data.name}${LAZY_DELIMITER}__react__lazy__component"

        // language=javascript
        val body = """
        const $componentName = lazy(() => 
            import("${data.originalFilePath}")
                .then(module => module.${data.export}())
                .then(component => ({ default: component }))
        )
        
        export const ${data.export} = () => $componentName
        """.trimIndent()

        return LazyItem(
            imports = IMPORTS,
            body = body,
        )
    }
}
