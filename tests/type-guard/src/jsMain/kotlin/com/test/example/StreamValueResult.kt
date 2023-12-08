package com.test.example

import seskar.js.JsTypeGuard

@JsTypeGuard(
    property = "done",
    value = "false",
)
sealed external class StreamValueResult : StreamResult
