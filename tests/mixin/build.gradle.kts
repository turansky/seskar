plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(projects.tests.unionData)

    jsMainImplementation(libs.wrappers.web)

    jsTestImplementation(libs.kotlin.testJs)
}
