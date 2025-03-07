plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(libs.seskar.core)

    jsMainImplementation(kotlinWrappers.web)

    commonTestImplementation(libs.kotlin.test)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
}
