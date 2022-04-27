rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.6.21"
        kotlin("js") version kotlinVersion

        val kfcVersion = "5.12.0"
        id("io.github.turansky.kfc.application") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

includeBuild("seskar")

include("test")
