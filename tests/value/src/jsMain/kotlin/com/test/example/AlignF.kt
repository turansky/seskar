package com.test.example

import seskar.js.JsValue

sealed external interface AlignF {
    companion object {
        @JsValue("TOP")
        fun top(): AlignF

        @JsValue("LEFT")
        fun left(): AlignF

        @JsValue("BOTTOM")
        fun bottom(): AlignF

        @JsValue("RIGHT")
        fun right(): AlignF
    }
}
