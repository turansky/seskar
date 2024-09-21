plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.browser)
}
