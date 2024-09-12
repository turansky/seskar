plugins {
    alias(libs.plugins.kfc.application)
    alias(libs.plugins.seskar)
}

dependencies {
    jsMainImplementation(kotlinWrappers.emotion)
    jsMainImplementation(kotlinWrappers.react)
    jsMainImplementation(kotlinWrappers.reactDom)

    jsMainImplementation(projects.tests.lazy.content)
    jsMainImplementation(projects.tests.lazy.header)
    jsMainImplementation(projects.tests.lazy.footer)
}
