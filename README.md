[![CI Status](https://github.com/turansky/seskar/workflows/CI/badge.svg)](https://github.com/turansky/seskar/actions)
[![CI Status](https://github.com/turansky/seskar/workflows/gradle%20plugin/badge.svg)](https://github.com/turansky/seskar/actions)
[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/io.github.turansky.seskar?logo=gradle)](https://plugins.gradle.org/plugin/io.github.turansky.seskar)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.turansky.seskar/seskar-core?logo=apache-maven)](https://mvnrepository.com/artifact/io.github.turansky.seskar/seskar-core)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.10-blue.svg?logo=kotlin)](http://kotlinlang.org)

# Seskar

Seskar is a gradle plugin that provides useful additions for Kotlin/JS projects. 

## Setup

To add Seskar to your project, you need to the following configuration to your project's `build.gradle.kts`:

```kotlin
plugins {
    kotlin("multiplatform") version "1.9.10"
    id("io.github.turansky.seskar") version "2.2.0"
}

// browser target
dependencies {
    implementation("io.github.turansky.seskar:seskar-core:2.2.0")
}
```

## React

#### Dependencies

When a project uses the Kotlin/JS compiler, `value classes` are autoboxed. If a `value class` is used as a dependency
of a react hook (e.g., in `useMemo`, `useState` or `useEffect`), a new class will be created on every rendering pass,
which causes infinite re-rendering.

To prevent this, Seskar disables autoboxing for `value class` dependencies in hooks.
Also, it converts `Long` values to `String`.

Seskar supports `Duration` by default.

##### Example

```kotlin
value class Count(
    private val value: Int,
)

val Counter = FC {
    val count: Count = useCount()
    
    useEffect(count) {
        println("Count changed: $count")
    }
}
```

##### Without plugin

```javascript
function Counter() { 
    var count = useCount()
    
    useEffect(
        () => {
            println(`Count changed: $count`)
        },
        // AUTOBOXING
        [ new Count(count) ],
    )
}
```

##### With plugin

```javascript
function Counter() { 
    var count = useCount()
    
    useEffect(
        () => {
            println(`Count changed: $count`)
        },
        // NO AUTOBOXING
        [ count ],
    )
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
sealed external interface Align {
    companion object {
        val TOP: Align
        val LEFT: Align
        val BOTTOM: Align
        val RIGHT: Align
    }
}

println(Align.TOP)  // 'TOP'
println(Align.LEFT) // 'LEFT'
```

#### Custom

Use `String` or `Int` constant as union value

##### `String`

```typescript
// TypeScript
type Align = 't' | 'l' | 'b' | 'r'
```

```kotlin
// Kotlin
import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
sealed external interface CustomAlign {
    companion object {
        @JsValue("t")
        val TOP: CustomAlign

        @JsValue("l")
        val LEFT: CustomAlign

        @JsValue("b")
        val BOTTOM: CustomAlign

        @JsValue("r")
        val RIGHT: CustomAlign
    }
}

println(CustomAlign.TOP)  // 't'
println(CustomAlign.LEFT) // 'l'
```

##### `Int`

```typescript
// TypeScript
type GRAPH_ITEM_TYPE_NODE = 1
type GRAPH_ITEM_TYPE_EDGE = 2
type GRAPH_ITEM_TYPE_PORT = 3
type GRAPH_ITEM_TYPE_LABEL = 4

type GraphItemType = GRAPH_ITEM_TYPE_NODE
    | GRAPH_ITEM_TYPE_EDGE
    | GRAPH_ITEM_TYPE_PORT
    | GRAPH_ITEM_TYPE_LABEL
```

```kotlin
// Kotlin
import seskar.js.JsIntValue
import seskar.js.JsUnion

@JsUnion
sealed external interface GraphItemType {
    companion object {
        @JsIntValue(1)
        val NODE: GraphItemType

        @JsIntValue(2)
        val EDGE: GraphItemType

        @JsIntValue(4)
        val PORT: GraphItemType

        @JsIntValue(8)
        val LABEL: GraphItemType
    }
}

println(GraphItemType.EDGE) // 2
println(GraphItemType.PORT) // 4
```
