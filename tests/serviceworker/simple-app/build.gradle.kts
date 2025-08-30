plugins {
    alias(kfc.plugins.application)
    alias(libs.plugins.seskar)
}

dependencies {
    commonMainImplementation(kotlinWrappers.browser)
    commonMainImplementation(libs.coroutines.core)
}
