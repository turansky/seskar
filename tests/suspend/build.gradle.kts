plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.web)

    jsTestImplementation(libs.kotlin.testJs)
    jsTestImplementation(libs.coroutines.core)
    jsTestImplementation(libs.coroutines.test)
}
