plugins {
    id("io.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    implementation(project(":tests:data"))
    implementation("io.github.turansky.seskar:seskar-core")
    testImplementation(kotlin("test-js"))
}
