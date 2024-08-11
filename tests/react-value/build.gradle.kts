plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(project(":tests:data"))
    jsMainImplementation(libs.wrappers.react.dom)
    jsMainImplementation(libs.wrappers.react.use)

    jsTestImplementation(libs.kotlin.test.js)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(project(":tests:react-test"))
    jsTestImplementation(libs.wrappers.react.dom.test.utils)
}
