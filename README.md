<!--
[![CI Status](https://github.com/turansky/seskar/workflows/CI/badge.svg)](https://github.com/turansky/seskar/actions)
-->
[![CI Status](https://github.com/turansky/seskar/workflows/gradle%20plugin/badge.svg)](https://github.com/turansky/seskar/actions)
[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/github/turansky/seskar/com.github.turansky.seskar.gradle.plugin/maven-metadata.xml.svg?label=plugin&logo=gradle)](https://plugins.gradle.org/plugin/com.github.turansky.seskar)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.turansky.seskar/seskar-core?logo=apache-maven)](https://mvnrepository.com/artifact/com.github.turansky.seskar/seskar-core)
[![Kotlin](https://img.shields.io/badge/kotlin-1.5.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlin/JS. IR supported](https://img.shields.io/badge/kotlin-IR%20only-yellow?logo=kotlin&logoColor=yellow)](https://kotl.in/jsirsupported)

# Seskar

## Compilers
IR only

## Setup
```kotlin
plugins {
    kotlin("js") version "1.5.0"
    id("com.github.turansky.seskar") version "0.0.7"
}

// IR browser target

dependencies {
    implementation("com.github.turansky.seskar:seskar-core:0.0.7")
}
```

## Unions

#### AS-IS

Use enum constant as union value

```typescript
// TypeScript
type Align = 'TOP' | 'LEFT' | 'BOTTOM' | 'RIGHT'
```

```kotlin
// Kotlin
import seskar.js.JsUnion

@JsUnion
external enum class Align {
    TOP,
    LEFT,
    BOTTOM,
    RIGHT,

    ;
}

println(Align.TOP)  // 'TOP'
println(Align.LEFT) // 'LEFT'
```

#### Custom

Use custom value as union value

```typescript
// TypeScript
type Align = 't' | 'l' | 'b' | 'r'
```

```kotlin
// Kotlin
import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
external enum class CustomAlign {
    @JsString("t")
    TOP,

    @JsString("l")
    LEFT,

    @JsString("b")
    BOTTOM,

    @JsString("r")
    RIGHT,

    ;
}

println(CustomAlign.TOP)  // 't'
println(CustomAlign.LEFT) // 'l'
```

## How it works?

TBD

## Plans

#### Case configuration

```kotlin
import seskar.js.JsUnion
import seskar.Case

@JsUnion(Case.KEBAB)
external enum class Align {
    TOP_LEFT,     // 'top-left'
    TOP_RIGHT,    // 'top-right' 
    BOTTOM_LEFT,  // 'bottom-left'
    BOTTOM_RIGHT, // 'bottom-right'

    ;
}
``` 
