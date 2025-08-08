rootProject.name = "seskar"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            val kotlinVersion = extra["kotlin.version"] as String
            plugin("kotlin-multiplatform", "org.jetbrains.kotlin.multiplatform").version(kotlinVersion)
            plugin("kotlin-jsPlainObjects", "org.jetbrains.kotlin.plugin.js-plain-objects").version(kotlinVersion)

            val seskarVersion = "--predefined--"
            plugin("seskar", "io.github.turansky.seskar").version(seskarVersion)

            library("kotlin-test", "org.jetbrains.kotlin", "kotlin-test").version(kotlinVersion)

            val coroutinesVersion = extra["kotlinx-coroutines.version"] as String
            library("coroutines-core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version(coroutinesVersion)
            library("coroutines-test", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").version(coroutinesVersion)
        }

        create("kfc") {
            val kfcVersion = extra["kfc.version"] as String
            plugin("application", "io.github.turansky.kfc.application").version(kfcVersion)
            plugin("library", "io.github.turansky.kfc.library").version(kfcVersion)
        }

        create("kotlinWrappers") {
            val wrappersVersion = extra["kotlin-wrappers.version"] as String
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:$wrappersVersion")
        }
    }
}

includeBuild("seskar")

include("tests:alias")
include("tests:data")
include("tests:events")
include("tests:jsany")
include("tests:lazy-functions")
include("tests:mixin")
include("tests:native")
include("tests:react-display-name")
include("tests:react-lazy-components:app")
include("tests:react-lazy-components:content")
include("tests:react-lazy-components:footer")
include("tests:react-lazy-components:header")
include("tests:react-memo")
include("tests:react-props")
include("tests:react-test")
include("tests:react-value")
include("tests:suspend")
include("tests:type-guard")
include("tests:union-data")
include("tests:value")
include("tests:workers:simple-app")
include("tests:workers:simple-check")
