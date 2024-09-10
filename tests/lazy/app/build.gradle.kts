plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(libs.wrappers.react)
    jsMainImplementation(libs.wrappers.reactDom)

    jsMainImplementation(projects.tests.lazy.content)
    jsMainImplementation(projects.tests.lazy.header)
    jsMainImplementation(projects.tests.lazy.footer)
}
