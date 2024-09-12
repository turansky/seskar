plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(projects.tests.data)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.reactUse)

    jsTestImplementation(libs.kotlin.testJs)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(projects.tests.reactTest)
    jsTestImplementation(kotlinWrappers.reactDomTestUtils)
}
