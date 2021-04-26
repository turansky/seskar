plugins {
    kotlin("jvm")
    id("com.github.turansky.kfc.maven-central-publish") apply false
}

// TODO: fix configuration
apply(plugin = "com.github.turansky.kfc.maven-central-publish")

dependencies {
    compileOnly(kotlin("compiler-embeddable"))
}
