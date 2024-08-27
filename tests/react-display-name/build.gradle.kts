plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(libs.wrappers.react)
    jsMainImplementation(libs.wrappers.react.dom)
    jsMainImplementation(libs.wrappers.react.use)

    jsTestImplementation(libs.kotlin.test.js)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(projects.tests.reactTest)
    jsTestImplementation(libs.wrappers.react.dom.test.utils)
}
