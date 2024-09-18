package seskar.gradle.plugin

class LazyFunctionFactory :
    LazyItemFactory {
    override fun create(
        data: LazyItemData,
    ): LazyItem {
        // language=javascript
        val body = """
        export const ${data.export} = () => {
            return import("${data.originalFilePath}")
                .then(module => module.${data.export}())
        }
        """.trimIndent()

        return LazyItem(
            body = body,
        )
    }
}
