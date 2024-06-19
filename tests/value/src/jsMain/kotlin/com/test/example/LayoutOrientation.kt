@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsValue

sealed external interface LayoutOrientationK {
    companion object {
        @JsValue("top-to-bottom")
        val TOP_TO_BOTTOM: LayoutOrientationK

        @JsValue("left-to-right")
        val LEFT_TO_RIGHT: LayoutOrientationK

        @JsValue("bottom-to-top")
        val bottomToTop: LayoutOrientationK

        @JsValue("right-to-left")
        val rightToLeft: LayoutOrientationK
    }
}

sealed external interface LayoutOrientationS {
    companion object {
        @JsValue("top_to_bottom")
        val TOP_TO_BOTTOM: LayoutOrientationS

        @JsValue("left_to_right")
        val LEFT_TO_RIGHT: LayoutOrientationS

        @JsValue("bottom_to_top")
        val bottomToTop: LayoutOrientationS

        @JsValue("right_to_left")
        val rightToLeft: LayoutOrientationS
    }
}
