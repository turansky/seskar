package com.test.example

import seskar.js.JsSpecialName

external interface HtmlAttributes : AriaAttributes {
    @JsSpecialName("aria-label")
    override var ariaLabel: String
}
