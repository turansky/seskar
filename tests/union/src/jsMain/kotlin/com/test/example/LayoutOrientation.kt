@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.Case
import seskar.js.JsUnion

@JsUnion(case = Case.KEBAB)
sealed external interface LayoutOrientationK {
    companion object {
        val TOP_TO_BOTTOM: LayoutOrientationK
        val LEFT_TO_RIGHT: LayoutOrientationK
        val bottomToTop: LayoutOrientationK
        val rightToLeft: LayoutOrientationK
    }
}

@JsUnion(case = Case.SNAKE)
sealed external interface LayoutOrientationS {
    companion object {
        val TOP_TO_BOTTOM: LayoutOrientationS
        val LEFT_TO_RIGHT: LayoutOrientationS
        val bottomToTop: LayoutOrientationS
        val rightToLeft: LayoutOrientationS
    }
}
