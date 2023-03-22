[![CI Status](https://github.com/turansky/seskar/workflows/CI/badge.svg)](https://github.com/turansky/seskar/actions)
[![CI Status](https://github.com/turansky/seskar/workflows/gradle%20plugin/badge.svg)](https://github.com/turansky/seskar/actions)
[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/io.github.turansky.seskar?logo=gradle)](https://plugins.gradle.org/plugin/io.github.turansky.seskar)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.turansky.seskar/seskar-core?logo=apache-maven)](https://mvnrepository.com/artifact/io.github.turansky.seskar/seskar-core)
[![Kotlin](https://img.shields.io/badge/kotlin-1.8.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlin/JS. IR supported](https://img.shields.io/badge/kotlin-IR%20supported-yellow?logo=kotlin&logoColor=yellow)](https://kotl.in/jsirsupported)

# Seskar

Seskar is a gradle plugin that provides useful additions for Kotlin/JS projects. 

## Setup

To add Seskar to your project, you need to the following configuration to your project's `build.gradle.kts`:

```kotlin
plugins {
    kotlin("js") version "1.8.10"
    id("io.github.turansky.seskar") version "1.3.0"
}

// browser target
dependencies {
    implementation("io.github.turansky.seskar:seskar-core:1.3.0")
}
```

## React

#### Props

Seskar checks if interfaces that inherit from the `Props` interface are external.

#### Dependencies [IR] 

When a project uses the Kotlin/JS IR compiler, `value classes` are autoboxed. If a `value class` is used as a dependency 
of a react hook (e.g., in `useMemo`, `useState` or `useEffect`), a new class will be created on every rendering pass, 
which causes infinite re-rendering. 

To prevent this, Seskar provides a `@JsValue` marker, which disables autoboxing for `value class` dependencies in hooks. 
Also, it converts `Long` values to `String`.

Seskar supports `Duration` by default, so no `@JsValue` marker is needed.

##### @JsValue - example  

```kotlin
import seskar.js.JsValue

@JsValue
value class Count(
    private val value: Int,
)

val Counter = VFC {
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

## Unions [IR]

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

#### Kebab case

```typescript
// TypeScript
type LayoutOrientation = 'top-to-bottom' 
    | 'left-to-right'
    | 'bottom-to-top'
    | 'right-to-left'
```

```kotlin
// Kotlin
import seskar.js.JsUnion
import seskar.js.Case

@JsUnion(case = Case.KEBAB)
external enum class LayoutOrientation {
    TOP_TO_BOTTOM, // 'top-to-bottom'
    LEFT_TO_RIGHT, // 'left-to-right'
    bottomToTop,   // 'bottom-to-top'
    rightToLeft,   // 'right-to-left'

    ;
}
```

#### Snake case

```typescript
// TypeScript
type LayoutOrientation = 'top_to_bottom' 
    | 'left_to_right'
    | 'bottom_to_top'
    | 'right_to_left'
```

```kotlin
// Kotlin
import seskar.js.JsUnion
import seskar.js.Case

@JsUnion(case = Case.SNAKE)
external enum class LayoutOrientation {
    TOP_TO_BOTTOM, // 'top_to_bottom'
    LEFT_TO_RIGHT, // 'left_to_right'
    bottomToTop,   // 'bottom_to_top'
    rightToLeft,   // 'right_to_left'

    ;
}
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
import seskar.js.JsInt
import seskar.js.JsValue

@JsUnion
external enum class GraphItemType {
    @JsInt(1)
    NODE,

    @JsInt(2)
    EDGE,

    @JsInt(4)
    PORT,

    @JsInt(8)
    LABEL,

    ;
}

println(GraphItemType.EDGE) // 2
println(GraphItemType.PORT) // 4
```

## How it works?

TBD
