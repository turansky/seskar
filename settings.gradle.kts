rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("js") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("io.github.turansky.kfc.application") version kfcVersion
        id("io.github.turansky.kfc.wrappers") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

includeBuild("seskar")

include("tests:data")
include("tests:memo")
include("tests:react")
include("tests:react-test")
include("tests:union")
include("tests:value")
