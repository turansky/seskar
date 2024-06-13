plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.js-plain-objects") apply false
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar") version "3.0.0"
}

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core:3.0.0")

    jsTestImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "8.8"
}
