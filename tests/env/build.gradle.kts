plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    jsTestImplementation(kotlin("test-js"))
}

tasks.patchBundlerConfig {
    env("BUILD_NUMBER", "generic-number")
    env("NUMBER", "42")
}
