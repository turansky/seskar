package seskar.gradle.plugin

class LazyFunctionFactory :
    LazyItemFactory {
    override fun create(
        data: LazyItemData,
    ): LazyItem {
        val lazyFunction = "${data.name}\$\$__lazy__function"

        // language=javascript
        val body = """
        const $lazyFunction = import("${data.originalFilePath}")
                .then(module => module.${data.export})
        
        export const ${data.export} = () => $lazyFunction
        """.trimIndent()

        return LazyItem(
            body = body,
        )
    }
}
