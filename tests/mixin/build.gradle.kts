plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(project(":tests:union-data"))

    jsMainImplementation(libs.wrappers.web)

    jsTestImplementation(libs.kotlin.test.js)
}
