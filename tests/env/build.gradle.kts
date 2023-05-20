plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation("io.github.turansky.seskar:seskar-core")

    jsTestImplementation(kotlin("test-js"))
}

tasks.patchWebpackConfig {
    env("BUILD_NUMBER", "generic-number")
    env("NUMBER", "42")
}
