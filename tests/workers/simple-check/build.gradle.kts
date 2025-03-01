plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(libs.coroutines.core)

    commonTestImplementation(libs.kotlin.test)
    jsTestImplementation(libs.coroutines.test)
}
