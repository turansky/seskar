import org.gradle.api.internal.catalog.DefaultVersionCatalogBuilder

rootProject.name = "seskar"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        register("kfc") {
            val kfcVersion = extra["kfc.version"] as String
            plugin("application", "io.github.turansky.kfc.application").version(kfcVersion)
            plugin("library", "io.github.turansky.kfc.library").version(kfcVersion)
        }

        register("kotlinWrappers") {
            val kotlinWrappersCatalog = named("libs")
                .map { it as DefaultVersionCatalogBuilder }
                .map { it.build() }
                .map { it.getDependencyData("catalogs.kotlinWrappers") }
                .map { "${it.group}:${it.name}:${it.version}" }
                .get()

            from(kotlinWrappersCatalog)
        }
    }
}

includeBuild("seskar")

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
include("tests:serviceworker:simple-app")
include("tests:suspend")
include("tests:union-data")
include("tests:value")
include("tests:workers:simple-app")
include("tests:workers:simple-check")
include("tests:worklets:mdn-audioworklet-example")
