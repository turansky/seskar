package seskar.gradle.plugin

class LazyConfiguration(
    val compileTask: String,
    val syncTask: String,
    val generateTask: String,
) {
    companion object {
        val ALL: List<LazyConfiguration> =
            Platform.ALL.flatMap { (prefix, suffix) ->
                sequenceOf("", "Test").flatMap { groupId ->
                    sequenceOf("Production", "Development").map { mode ->
                        LazyConfiguration(
                            compileTask = "compile${groupId}${mode}ExecutableKotlin${suffix}",
                            syncTask = "${prefix}${groupId}${groupId}${mode}ExecutableCompileSync",
                            generateTask = "${prefix}${groupId}${mode}GenerateLazyModules",
                        )
                    }
                }
            }.toList()
    }
}

private data class Platform(
    val prefix: String,
    val suffix: String = prefix.replaceFirstChar { it.uppercase() },
) {
    companion object {
        val ALL: List<Platform> = listOf(
            Platform("js"),
            // Platform("wasmJs"),
        )
    }
}
