rootProject.name = "seskar"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.js-plain-objects") version kotlinVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            val kfcVersion = extra["kfc.version"] as String
            plugin("kfc-application", "io.github.turansky.kfc.application").version(kfcVersion)

            val seskarVersion = "--predefined--"
            plugin("seskar", "io.github.turansky.seskar").version(seskarVersion)

            val kotlinVersion = extra["kotlin.version"] as String
            library("kotlin-test-js", "org.jetbrains.kotlin", "kotlin-test-js").version(kotlinVersion)

            val wrappersVersion = extra["kotlin-wrappers.version"] as String
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:$wrappersVersion")

            val coroutinesVersion = extra["kotlinx-coroutines.version"] as String
            library("coroutines-core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version(coroutinesVersion)
            library("coroutines-test", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").version(coroutinesVersion)
        }
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
