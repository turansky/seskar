package com.example.header

import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span

internal val HeaderContent = FC {
    div {
        +"Header Content"

        span {
            +"SUB CONTENT"
        }
    }
}
