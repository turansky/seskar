plugins {
    `kotlin-dsl`

    id("com.gradle.plugin-publish")
    id("io.github.turansky.kfc.plugin-publish")
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))
}

val REPO_URL = "https://github.com/turansky/seskar"

gradlePlugin {
    website = REPO_URL
    vcsUrl = REPO_URL

    plugins {
        create("seskar") {
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
