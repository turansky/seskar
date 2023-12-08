package com.test.example

import seskar.js.JsTypeGuard

@JsTypeGuard(
    property = "done",
    value = "true",
)
sealed external class StreamDoneResult : StreamResult
