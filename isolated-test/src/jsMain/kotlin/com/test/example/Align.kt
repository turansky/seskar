package com.test.example

import seskar.js.JsValue

sealed external interface Align {
    companion object {
        @JsValue("TOP")
        val TOP: Align

        @JsValue("LEFT")
        val LEFT: Align

        @JsValue("BOTTOM")
        val BOTTOM: Align

        @JsValue("RIGHT")
        val RIGHT: Align
    }
}
