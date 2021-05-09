package com.test.example

import seskar.js.Case
import seskar.js.JsUnion

@JsUnion(case = Case.KEBAB)
external enum class LayoutOrientationK {
    TOP_TO_BOTTOM,
    LEFT_TO_RIGHT,
    bottomToTop,
    rightToLeft,

    ;
}

@JsUnion(case = Case.SNAKE)
external enum class LayoutOrientationS {
    TOP_TO_BOTTOM,
    LEFT_TO_RIGHT,
    bottomToTop,
    rightToLeft,

    ;
}
