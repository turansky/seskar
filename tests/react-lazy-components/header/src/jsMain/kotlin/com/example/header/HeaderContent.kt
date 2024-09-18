package com.example.header

import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span

internal external interface HeaderContentProps : Props

/** */
internal val HeaderContent = FC<HeaderContentProps> {
    div {
        +"Header Content"
        span {
            +"SUB CONTENT"
        }
    }
}
