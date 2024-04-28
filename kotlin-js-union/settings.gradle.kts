rootProject.name = "kotlin-js-union"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.assignment") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("io.github.turansky.kfc.library") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("kotlin-js-union")
include("union-test")
