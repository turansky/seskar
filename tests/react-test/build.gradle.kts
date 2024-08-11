plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    jsMainImplementation(libs.wrappers.react)
    jsMainImplementation(libs.wrappers.react.dom)
    jsMainImplementation(libs.coroutines.core)
    jsMainImplementation(libs.coroutines.test)
    jsMainImplementation(libs.wrappers.react.dom.test.utils)
}
