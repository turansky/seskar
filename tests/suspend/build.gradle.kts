plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(libs.seskar.core)

    commonMainImplementation(kotlinWrappers.js)
    commonMainImplementation(kotlinWrappers.web)

    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.coroutines.core)
    commonTestImplementation(libs.coroutines.test)
}
