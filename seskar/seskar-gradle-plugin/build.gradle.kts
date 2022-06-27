plugins {
    id("com.gradle.plugin-publish")
    id("io.github.turansky.kfc.plugin-publish")
    kotlin("jvm")
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))
}

// TODO: remove after Gradle update
tasks.compileKotlin {
    kotlinOptions.allWarningsAsErrors = false
}

gradlePlugin {
    plugins {
        create("seskar") {
            id = "io.github.turansky.seskar"
            displayName = "Seskar"
            description = "Additions to Kotlin data classes"
            implementationClass = "seskar.gradle.plugin.SeskarGradleSubplugin"
        }
    }
}

val REPO_URL = "https://github.com/turansky/seskar"

pluginBundle {
    website = REPO_URL
    vcsUrl = REPO_URL

    pluginTags = mapOf(
        "seskar" to listOf(
            "kotlin",
            "dataclass",
            "equals",
            "hashcode",
        )
    )
}
