package com.test.memo

import react.FC
import react.Props

external interface CounterProps : Props {
    var title: String
}

val Counter = FC<CounterProps> { props ->
    +props.title
}
