package com.test.counter

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange

const val COUNTER_CONTAINER_ID = "container"

external interface CounterProps : Props {
    var active: Boolean
}

val Counter = FC<CounterProps> {
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
