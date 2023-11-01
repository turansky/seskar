@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
sealed external interface CustomAlignO {
    @JsValue("t")
    object TOP : CustomAlignO

    @JsValue("l")
    object LEFT : CustomAlignO

    @JsValue("b")
    object BOTTOM : CustomAlignO

    @JsValue("r")
    object RIGHT : CustomAlignO
}
