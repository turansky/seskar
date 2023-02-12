package com.test.example

import kotlin.test.Test

class LayoutOrientationTest {
    @Test
    fun kebab() {
        assertEquals("top-to-bottom", LayoutOrientationK.TOP_TO_BOTTOM)
        assertEquals("left-to-right", LayoutOrientationK.LEFT_TO_RIGHT)
        assertEquals("bottom-to-top", LayoutOrientationK.bottomToTop)
        assertEquals("right-to-left", LayoutOrientationK.rightToLeft)
    }

    @Test
    fun snake() {
        assertEquals("top_to_bottom", LayoutOrientationS.TOP_TO_BOTTOM)
        assertEquals("left_to_right", LayoutOrientationS.LEFT_TO_RIGHT)
        assertEquals("bottom_to_top", LayoutOrientationS.bottomToTop)
        assertEquals("right_to_left", LayoutOrientationS.rightToLeft)
    }
}
