[![CI Status](https://github.com/turansky/seskar/workflows/CI/badge.svg)](https://github.com/turansky/seskar/actions)
[![CI Status](https://github.com/turansky/seskar/workflows/gradle%20plugin/badge.svg)](https://github.com/turansky/seskar/actions)
[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/github/turansky/seskar/com.github.turansky.seskar.gradle.plugin/maven-metadata.xml.svg?label=plugin&logo=gradle)](https://plugins.gradle.org/plugin/com.github.turansky.seskar)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.turansky.seskar/seskar-core?logo=apache-maven)](https://mvnrepository.com/artifact/com.github.turansky.seskar/seskar-core)
[![Kotlin](https://img.shields.io/badge/kotlin-1.4.32-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlin/JS. IR supported](https://img.shields.io/badge/kotlin-IR%20supported-yellow?logo=kotlin&logoColor=yellow)](https://kotl.in/jsirsupported)

# Seskar

## Compilers

IR only

## Setup

```kotlin
plugins {
    kotlin("js") version "1.4.32"
    id("com.github.turansky.seskar") version "0.0.5"
}

// IR browser target

dependencies {
    implementation("com.github.turansky.seskar:seskar-core:0.0.5")
}
```
