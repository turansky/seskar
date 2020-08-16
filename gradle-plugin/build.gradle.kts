plugins {
    `java-gradle-plugin`

    id("com.gradle.plugin-publish") version "0.12.0"
    id("com.github.turansky.kfc.plugin-publish") version "0.11.0"

    kotlin("jvm") version "1.4.0"
}

repositories {
    jcenter()
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))
    compileOnly(kotlin("compiler-embeddable"))
}

pluginPublish {
    gradlePluginPrefix = true
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

// TODO: remove after migration on 1.4
tasks.compileKotlin {
    kotlinOptions.allWarningsAsErrors = false
}

tasks.wrapper {
    gradleVersion = "6.5.1"
    distributionType = Wrapper.DistributionType.ALL
}
