package com.test.union

import js.reflect.unsafeCast
import js.union.JsUnion

@JsUnion
external interface MyRequestType

inline val MyRequestType.Companion.GET: MyRequestType
    get() = unsafeCast("GET")

inline val MyRequestType.Companion.POST: MyRequestType
    get() = unsafeCast("POST")

inline val MyRequestType.Companion.UPDATE: MyRequestType
    get() = unsafeCast("UPDATE")
