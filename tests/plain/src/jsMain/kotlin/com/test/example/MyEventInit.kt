package com.test.example

import js.objects.JsPlainObject

@JsPlainObject
external interface MyEventInit {
    val bubbles: Boolean?
    val cancelable: Boolean?
    val composed: Boolean?
}
