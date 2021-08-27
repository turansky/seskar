rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("com.github.turansky.kfc.library") version kfcVersion
        id("com.github.turansky.kfc.maven-central-publish") version kfcVersion
        id("com.github.turansky.kfc.plugin-publish") version kfcVersion

        id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
        id("com.gradle.plugin-publish") version "0.15.0"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":seskar-core")

include(":seskar-compiler-plugin")
include(":seskar-gradle-plugin")
