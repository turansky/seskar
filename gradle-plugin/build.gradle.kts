plugins {
    `java-gradle-plugin`

    id("com.gradle.plugin-publish") version "0.14.0"
    id("com.github.turansky.kfc.plugin-publish") version "3.6.1"

    kotlin("jvm") version "1.4.32"
}

repositories {
    mavenCentral()
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

tasks.wrapper {
    gradleVersion = "7.0"
    distributionType = Wrapper.DistributionType.ALL
}
