plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.js-plain-objects") apply false
}

tasks.wrapper {
    gradleVersion = "8.8"
}
