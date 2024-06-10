rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.js-plain-objects") version kotlinVersion

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

include("tests:alias")
include("tests:data")
include("tests:env")
include("tests:mixin")
include("tests:native")
include("tests:primitive")
include("tests:react-display-name")
include("tests:react-memo")
include("tests:react-props")
include("tests:react-test")
include("tests:react-value")
include("tests:special-name")
include("tests:suspend")
include("tests:type-guard")
include("tests:union-data")
include("tests:value")
