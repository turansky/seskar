plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(projects.tests.data)
    jsMainImplementation(libs.wrappers.reactDom)
    jsMainImplementation(libs.wrappers.reactUse)

    jsTestImplementation(libs.kotlin.test.js)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(projects.tests.reactTest)
    jsTestImplementation(libs.wrappers.reactDomTestUtils)
}
