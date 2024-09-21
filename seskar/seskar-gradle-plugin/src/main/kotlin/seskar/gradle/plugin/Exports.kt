package seskar.gradle.plugin

internal object Exports {
    const val EMPTY_EXPORTS = "export {};"
    const val EXPORTS_SECTION_START = "\nexport {"
    const val EXPORTS_SECTION_END = "\n};"

    fun getExports(
        fileContent: String,
    ): List<String> {
        val exports = fileContent
            .substringAfter(EXPORTS_SECTION_START, "")
            .substringBefore(EXPORTS_SECTION_END, "")

        return parseExports(exports)
            .map { it.exportedName }
    }

    fun parseExports(
        exports: String,
    ): List<ExportData> =
        exports
            .splitToSequence("\n")
            .map { it.trim() }
            .map { it.removeSuffix(",") }
            .filter { it.isNotEmpty() }
            .map { parseExport(it) }
            .toList()

    private fun parseExport(
        export: String,
    ): ExportData {
        val (localName, exportedName) = export.split(" as ")
        return ExportData(
            localName = localName,
            exportedName = exportedName,
        )
    }
}
