plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(libs.coroutines.core)

    jsTestImplementation(libs.kotlin.testJs)
    jsTestImplementation(libs.coroutines.test)
}
