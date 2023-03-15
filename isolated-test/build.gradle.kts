plugins {
    kotlin("multiplatform") version "1.8.10"
    id("io.github.turansky.kfc.application") version "6.5.2"
    id("io.github.turansky.seskar") version "1.0.0"
}

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core:1.0.0")

    jsTestImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "7.6"
}
