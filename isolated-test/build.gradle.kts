plugins {
    kotlin("multiplatform") version "1.8.10"
    id("io.github.turansky.kfc.application") version "6.11.0"
    id("io.github.turansky.seskar") version "1.2.1"
}

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core:1.2.1")

    jsTestImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "8.0.2"
}
