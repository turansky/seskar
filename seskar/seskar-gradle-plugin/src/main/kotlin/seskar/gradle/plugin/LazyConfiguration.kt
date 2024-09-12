package seskar.gradle.plugin

class LazyConfiguration(
    val compileTask: String,
    val syncTask: String,
    val generateTask: String,
) {
    companion object {
        private val PRODUCTION = LazyConfiguration(
            compileTask = "compileProductionExecutableKotlinJs",
            syncTask = "jsProductionExecutableCompileSync",
            generateTask = "jsProductionGenerateLazyModules",
        )

        private val DEVELOPMENT = LazyConfiguration(
            compileTask = "compileDevelopmentExecutableKotlinJs",
            syncTask = "jsDevelopmentExecutableCompileSync",
            generateTask = "jsDevelopmentGenerateLazyModules",
        )

        val ALL: List<LazyConfiguration> = listOf(
            PRODUCTION,
            DEVELOPMENT,
        )
    }
}
