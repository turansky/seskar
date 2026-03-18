package com.test.example

sealed external interface MyHandler {
    operator fun invoke(x: Int, y: Int): Int
}
