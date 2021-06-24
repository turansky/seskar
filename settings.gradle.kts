rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.5.20"
        kotlin("js") version kotlinVersion

        val kfcVersion = "4.12.0"
        id("com.github.turansky.kfc.application") version kfcVersion
    }
}

includeBuild("seskar")

include("test")
