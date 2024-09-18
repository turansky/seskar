package com.test.lazy

import js.lazy.Lazy
import js.lazy.LazyFunction

@Lazy
val lazyFunction1 = LazyFunction {
    "42"
}
