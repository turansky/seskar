plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(projects.tests.data)
    jsMainImplementation(libs.wrappers.react.dom)
    jsMainImplementation(libs.wrappers.react.use)

    jsTestImplementation(libs.kotlin.test.js)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(projects.tests.reactTest)
    jsTestImplementation(libs.wrappers.react.dom.test.utils)
}
