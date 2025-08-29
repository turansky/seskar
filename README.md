[![CI Status](https://github.com/turansky/seskar/workflows/CI/badge.svg)](https://github.com/turansky/seskar/actions)
[![CI Status](https://github.com/turansky/seskar/workflows/gradle%20plugin/badge.svg)](https://github.com/turansky/seskar/actions)
[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/io.github.turansky.seskar?logo=gradle)](https://plugins.gradle.org/plugin/io.github.turansky.seskar)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.turansky.seskar/seskar-core?logo=apache-maven)](https://search.maven.org/artifact/io.github.turansky.seskar/seskar-core)
[![Kotlin](https://img.shields.io/badge/kotlin-2.2.10-blue.svg?logo=kotlin)](http://kotlinlang.org)

# Seskar

Seskar is a Gradle plugin that provides useful additions for Kotlin/JS projects.

## Setup

To add Seskar to your project, you need to add the following configuration to your project's `build.gradle.kts`:

```kotlin
plugins {
  kotlin("multiplatform") version "2.2.10"
    id("io.github.turansky.seskar") version "4.15.0"
}
```

### Kotlin/JS requirements

* Target -`es2015`
  * [example](https://github.com/JetBrains/kotlin-wrappers/blob/fdc8fb9b8ac2b13ba151449e13977a0327e7e3df/examples/buildSrc/src/main/kotlin/kotlin-conventions.gradle.kts#L35)
* Granularity - `per-file`
  * [example](https://github.com/JetBrains/kotlin-wrappers/blob/fdc8fb9b8ac2b13ba151449e13977a0327e7e3df/examples/gradle.properties#L7)

## Lazy functions

Puts a function in a separate JS chunk. The chunk will be loaded when the function is called the first time.

#### Setup

Add the dependency `kotlin-js` of [Kotlin Wrappers](https://github.com/JetBrains/kotlin-wrappers) to your project.

```kotlin
// root settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("kotlinWrappers") {
            val wrappersVersion = "%LAST_WRAPPERS_VERSION%"
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:$wrappersVersion")
        }
    }
}
```

```kotlin
// build.gradle.kts
sourceSets {
    jsMain {
        dependencies {
            implementation(kotlinWrappers.js)
        }
    }
}
```

#### Usage

```kotlin
// App.kt
suspend fun main() {
    console.log("App start!")
  
    val value = if (Random.nextDouble() > 0.5) {
        createCalculationWithHeavyLibrary()
    } else {
        42
    }

    console.log("Value: $value")
}

// createCalculationWithHeavyLibrary.kt
import js.lazy.Lazy
import js.lazy.LazyFunction

/**
 * Function will be located in separate JS chunk
 */ 
@Lazy
val createCalculationWithHeavyLibrary = LazyFunction<Int> {
    val calculator = HeavyCalculator()
    calculator.calculate()
}
```

## React

#### Lazy components

```kotlin
// Content.kt
@Lazy
val Content = FC {
    MyHeavyComponent1()
    MyHeavyComponent2()
}

// App.kt
val App = FC {
    Header()

    Suspense {
        Content()
    }

    Footer()
}
```

##### Examples

2. [Lazy app](/tests/react-lazy-components/app)
2. [Kotlin Wrappers Example](https://github.com/JetBrains/kotlin-wrappers/tree/master/examples/react-lazy-modules-webpack)

#### Conditional rendering

Seskar generates keys for child elements to prevent problems with conditional rendering.
As a result, in the following example `Content` child state won't be reset after `showHeader` property change.

```kotlin
val App = FC {
    val showHeader = useShowHeader()

    if (showHeader)
        Header() // generated: key = "@rdk/5"

    Content()    // generated: key = "@rdk/7"
    Footer()     // generated: key = "@rdk/8"
}
```

#### Dependencies

When a project uses the Kotlin/JS compiler, `value classes` are autoboxed. If a `value class` is used as a dependency
of a React hook (e.g., in `useMemo`, `useState` or `useEffect`), a new class will be created on every rendering pass,
which causes infinite re-rendering.

To prevent this, Seskar disables autoboxing for `value class` dependencies in hooks.
It also converts `Long` values to `String`.

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
        [new Count(count)],
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
        [count],
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

sealed external interface Align {
    companion object {
        @JsValue("TOP")
        val TOP: Align

        @JsValue("LEFT")
        val LEFT: Align

        @JsValue("BOTTOM")
        val BOTTOM: Align

        @JsValue("RIGHT")
        val RIGHT: Align
    }
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
import seskar.js.JsValue

sealed external interface LayoutOrientation {
    companion object {
        @JsValue("top-to-bottom")
        val TOP_TO_BOTTOM: LayoutOrientation

        @JsValue("left-to-right")
        val LEFT_TO_RIGHT: LayoutOrientation

        @JsValue("bottom-to-top")
        val bottomToTop: LayoutOrientation

        @JsValue("right-to-left")
        val rightToLeft: LayoutOrientation
    }
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
import seskar.js.Case

sealed external interface LayoutOrientation {
    companion object {
        @JsValue("top_to_bottom")
        val TOP_TO_BOTTOM: LayoutOrientation

        @JsValue("left_to_right")
        val LEFT_TO_RIGHT: LayoutOrientation

        @JsValue("bottom_to_top")
        val bottomToTop: LayoutOrientation

        @JsValue("right_to_left")
        val rightToLeft: LayoutOrientation
    }
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
import seskar.js.JsValue

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
import seskar.js.JsRawValue

""
sealed external interface GraphItemType {
    companion object {
      @JsRawValue("1")
        val NODE: GraphItemType

      @JsRawValue("2")
        val EDGE: GraphItemType

      @JsRawValue("4")
        val PORT: GraphItemType

      @JsRawValue("8")
        val LABEL: GraphItemType
    }
}

println(GraphItemType.EDGE) // 2
println(GraphItemType.PORT) // 4
```
