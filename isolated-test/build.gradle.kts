plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.js-plain-objects") apply false
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar") version "3.13.0"
}

dependencies {
    jsTestImplementation(kotlin("test-js"))
}

tasks.wrapper {
    gradleVersion = "8.10"
}
