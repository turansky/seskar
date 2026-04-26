plugins {
    `kotlin-dsl`

    alias(libs.plugins.gradlePluginPublish)
    alias(kfc.plugins.pluginPublish)
}

dependencies {
    compileOnly(libs.gradlePlugins.kotlin)
}

val REPO_URL = "https://github.com/turansky/seskar"

gradlePlugin {
    website = REPO_URL
    vcsUrl = REPO_URL

    plugins {
        register("seskar") {
            id = "io.github.turansky.seskar"
            displayName = "Seskar"
            description = "Additions to Kotlin data classes"
            implementationClass = "seskar.gradle.plugin.SeskarGradleSubplugin"
            tags = listOf(
                "kotlin",
                "react",
                "memo",
                "unions",
            )
        }
    }
}
