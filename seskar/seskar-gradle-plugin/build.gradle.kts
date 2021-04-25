plugins {
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")
    kotlin("jvm")
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))
}

gradlePlugin {
    plugins {
        create("seskar") {
            id = "com.github.turansky.seskar"
            implementationClass = "com.github.turansky.seskar.gradle.plugin.SeskarGradleSubplugin"
        }
    }
}

val REPO_URL = "https://github.com/turansky/seskar"

pluginBundle {
    website = REPO_URL
    vcsUrl = REPO_URL

    plugins.getByName("seskar") {
        displayName = "Seskar"
        description = "Additions to Kotlin data classes"
        tags = listOf(
            "kotlin",
            "dataclass",
            "equals",
            "hashcode"
        )
        version = project.version.toString()
    }
}
