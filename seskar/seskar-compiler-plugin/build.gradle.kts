plugins {
    kotlin("jvm")
    id("io.github.turansky.kfc.maven-central-publish")
}

dependencies {
    compileOnly(kotlin("compiler-embeddable"))
}
