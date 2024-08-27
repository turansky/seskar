plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(libs.wrappers.web)

    jsTestImplementation(libs.kotlin.test.js)
}
