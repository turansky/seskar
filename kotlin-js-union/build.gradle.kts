plugins {
    kotlin("multiplatform")
    kotlin("plugin.assignment")
    id("io.github.turansky.kfc.library")
}

dependencies {
    jsTestImplementation(kotlin("test-js"))
}

assignment {
    annotation("js.union.AssignmentOverload")
}
