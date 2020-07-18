plugins {
    kotlin("js")
    id("com.github.turansky.seskar")
}

kotlin.js {
    browser()

    binaries.executable()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}
