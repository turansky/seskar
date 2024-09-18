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

        private val TEST_PRODUCTION = LazyConfiguration(
            compileTask = "compileTestProductionExecutableKotlinJs",
            syncTask = "jsTestTestProductionExecutableCompileSync",
            generateTask = "jsTestProductionGenerateLazyModules",
        )

        private val TEST_DEVELOPMENT = LazyConfiguration(
            compileTask = "compileTestDevelopmentExecutableKotlinJs",
            syncTask = "jsTestTestDevelopmentExecutableCompileSync",
            generateTask = "jsTestDevelopmentGenerateLazyModules",
        )

        val ALL: List<LazyConfiguration> = listOf(
            PRODUCTION,
            DEVELOPMENT,

            TEST_PRODUCTION,
            TEST_DEVELOPMENT,
        )
    }
}
