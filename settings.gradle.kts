rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("multiplatform") version kotlinVersion

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
include("tests:display-name")
include("tests:env")
include("tests:memo")
include("tests:native")
include("tests:primitive")
include("tests:react-props")
include("tests:react-test")
include("tests:special-name")
include("tests:type-guard")
include("tests:union")
include("tests:union-data")
include("tests:value")
