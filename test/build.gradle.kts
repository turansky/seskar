plugins {
    id("com.github.turansky.kfc.application")
    id("io.github.turansky.seskar")
}

dependencies {
    implementation("io.github.turansky.seskar:seskar-core")
    testImplementation(kotlin("test-js"))
}
