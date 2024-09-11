package seskar.gradle.plugin

class LazyConfiguration(
    val compileTask: String,
    val syncTask: String,
) {
    companion object {
        val PRODUCTION = LazyConfiguration(
            compileTask = "compileProductionExecutableKotlinJs",
            syncTask = "jsProductionExecutableCompileSync",
        )

        val DEVELOPMENT = LazyConfiguration(
            compileTask = "compileDevelopmentExecutableKotlinJs",
            syncTask = "jsDevelopmentExecutableCompileSync",
        )
    }
}
