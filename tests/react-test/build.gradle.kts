plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)
    commonMainImplementation(libs.coroutines.core)
    commonMainImplementation(libs.coroutines.test)
    jsMainImplementation(kotlinWrappers.reactDomTestUtils)
}
