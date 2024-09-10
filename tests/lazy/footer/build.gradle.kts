plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(libs.wrappers.react)
    jsMainImplementation(libs.wrappers.reactDom)
}
