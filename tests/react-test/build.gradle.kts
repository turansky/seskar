plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(libs.wrappers.react)
    jsMainImplementation(libs.wrappers.react.dom)
    jsMainImplementation(libs.coroutines.core)
    jsMainImplementation(libs.coroutines.test)
    jsMainImplementation(libs.wrappers.react.dom.test.utils)
}
