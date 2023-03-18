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

val REPO_URL = "https://github.com/turansky/seskar"

gradlePlugin {
    website.set(REPO_URL)
    vcsUrl.set(REPO_URL)

    plugins {
        create("seskar") {
            id = "io.github.turansky.seskar"
            displayName = "Seskar"
            description = "Additions to Kotlin data classes"
            implementationClass = "seskar.gradle.plugin.SeskarGradleSubplugin"
            tags.set(
                listOf(
                    "kotlin",
                    "dataclass",
                    "equals",
                    "hashcode"
                )
            )
        }
    }
}
