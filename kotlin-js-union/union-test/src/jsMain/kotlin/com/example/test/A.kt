package com.example.test

import js.union.Union2
import js.union.Union2Impl

internal class A {
    val a: Union2<String, Int>
        get() = Union2Impl(this, "aaa")
}
