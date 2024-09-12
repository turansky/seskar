rootProject.name = "seskar-isolated-test"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            val kotlinVersion = extra["kotlin.version"] as String
            plugin("kotlin-multiplatform", "org.jetbrains.kotlin.multiplatform").version(kotlinVersion)
            plugin("kotlin-jsPlainObjects", "org.jetbrains.kotlin.plugin.js-plain-objects").version(kotlinVersion)

            val seskarVersion = extra["seskar.version"] as String
            plugin("seskar", "io.github.turansky.seskar").version(seskarVersion)

            library("kotlin-testJs", "org.jetbrains.kotlin", "kotlin-test-js").version(kotlinVersion)
        }

        create("kfc") {
            val kfcVersion = extra["kfc.version"] as String
            plugin("application", "io.github.turansky.kfc.application").version(kfcVersion)
        }
    }
}
