rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.6.0"
        kotlin("js") version kotlinVersion

        val kfcVersion = "4.60.0"
        id("com.github.turansky.kfc.application") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

includeBuild("seskar")

include("test")
