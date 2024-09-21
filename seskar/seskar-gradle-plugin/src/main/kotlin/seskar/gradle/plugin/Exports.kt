package seskar.gradle.plugin

internal object Exports {
    const val EMPTY_EXPORTS = "export {};"
    const val EXPORTS_SECTION_START = "\nexport {"
    const val EXPORTS_SECTION_END = "\n};"

    fun getExports(
        content: String,
    ): List<String> {
        return content
            .substringAfter(EXPORTS_SECTION_START, "")
            .substringBefore(EXPORTS_SECTION_END, "")
            .splitToSequence("\n")
            .map { it.trim() }
            .map { it.removeSuffix(",") }
            .filter { it.isNotEmpty() }
            .map { it.substringAfter(" as ") }
            .toList()
    }
}
