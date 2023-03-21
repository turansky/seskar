plugins {
    kotlin("multiplatform") version "1.8.10"
    id("io.github.turansky.kfc.application") version "6.12.1"
    id("io.github.turansky.seskar") version "1.2.2"
}

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core:1.2.2")

    jsTestImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "8.0.2"
}
