plugins {
    id("com.github.turansky.kfc.application")
    id("com.github.turansky.seskar")
}

dependencies {
    implementation("com.github.turansky.seskar:seskar-core")
    testImplementation(kotlin("test-js"))
}
