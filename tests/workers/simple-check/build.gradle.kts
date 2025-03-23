plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(kotlinWrappers.browser)
    commonMainImplementation(libs.coroutines.core)

    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.coroutines.test)
}
