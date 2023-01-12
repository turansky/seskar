package com.test.counter

import react.VFC
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange

const val COUNTER_CONTAINER_ID = "container"

val Counter = VFC {
    div {
        id = COUNTER_CONTAINER_ID

        onChange = {
            // TODO: implement
        }

        button {
            title = "First button"
        }

        button {
            title = "Second button"
        }
    }
}
