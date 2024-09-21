plugins {
    alias(kfc.plugins.application)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(libs.coroutines.core)
}
