plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)
    jsMainImplementation(libs.coroutines.core)
    jsMainImplementation(libs.coroutines.test)
    jsMainImplementation(kotlinWrappers.reactDomTestUtils)
}
