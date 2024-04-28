package com.test.memo

import react.Props
import web.dom.Element

const val DATA_COUNT = "data-count"

var Props.dataCount: Int?
    get() = asDynamic()[DATA_COUNT]
    set(value) {
        asDynamic()[DATA_COUNT] = value
    }

val Element.dataCount: Int?
    get() = getAttribute(DATA_COUNT)?.toInt()
