rootProject.name = "seskar"

pluginManagement {
    // TODO: remove after issue fix
    //  https://youtrack.jetbrains.com/issue/KT-57687
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("io.github.turansky.kfc.library") version kfcVersion
        id("io.github.turansky.kfc.maven-central-publish") version kfcVersion
        id("io.github.turansky.kfc.plugin-publish") version kfcVersion

        id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
        id("com.gradle.plugin-publish") version "1.1.0"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":seskar-core")
include(":seskar-react")

include(":seskar-compiler-plugin")
include(":seskar-gradle-plugin")
