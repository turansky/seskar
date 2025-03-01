plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(projects.tests.data)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(kotlinWrappers.reactUse)

    commonTestImplementation(libs.kotlin.test)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
    jsTestImplementation(projects.tests.reactTest)
    jsTestImplementation(kotlinWrappers.reactDomTestUtils)
}
