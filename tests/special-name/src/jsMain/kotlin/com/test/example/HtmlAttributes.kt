package com.test.example

import seskar.js.JsSpecialName

external interface HtmlAttributes :
    ElementAttributes,
    AriaAttributes {
    @JsSpecialName("aria-label")
    override var ariaLabel: String

    @JsSpecialName("get-data")
    override fun getData(): String
}
