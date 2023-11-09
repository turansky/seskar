package com.test.example

import seskar.js.JsSpecialName

external interface AriaAttributes {
    @JsSpecialName("aria-label")
    var ariaLabel: String
}
