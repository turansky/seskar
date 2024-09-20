package seskar.gradle.plugin

internal object Exports {
    const val EMPTY_EXPORTS = "export {}"

    fun getExports(
        content: String,
    ): List<String> =
        content
            .substringAfter("\nexport {", "")
            .substringBefore("\n};", "")
            .splitToSequence("\n")
            .map { it.trim() }
            .map { it.removeSuffix(",") }
            .filter { it.isNotEmpty() }
            .map { it.substringAfter(" as ") }
            .toList()

}
