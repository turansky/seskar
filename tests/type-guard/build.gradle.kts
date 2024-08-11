plugins {
    alias(libs.plugins.kfc.application)
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(libs.wrappers.web)

    jsTestImplementation(libs.kotlin.test.js)
}
