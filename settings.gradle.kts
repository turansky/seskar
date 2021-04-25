rootProject.name = "seskar"

pluginManagement {
    plugins {
        val kotlinVersion = "1.4.32"
        kotlin("js") version kotlinVersion

        val kfcVersion = "3.6.1"
        id("com.github.turansky.kfc.application") version kfcVersion
    }
}

includeBuild("seskar")

include("example")
include("test")
