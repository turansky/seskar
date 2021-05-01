plugins {
    kotlin("jvm")
    id("com.github.turansky.kfc.maven-central-publish")
}

dependencies {
    compileOnly(kotlin("compiler-embeddable"))
}
