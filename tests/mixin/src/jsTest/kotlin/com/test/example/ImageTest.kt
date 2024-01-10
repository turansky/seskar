package com.test.example

import kotlin.test.Test
import kotlin.test.assertIs

class ImageTest {
    @Test
    fun test() {
        val image = Image()

        assertIs<HTMLElement>(image)
    }
}
