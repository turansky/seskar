package com.example.app

import com.example.content.Content
import com.example.footer.Footer
import com.example.header.Header
import emotion.react.css
import react.FC
import react.Suspense
import react.dom.html.ReactHTML.div
import web.cssom.Display

val App = FC {
    div {
        css {
            display = Display.grid
        }

        Suspense {
            Header()
        }

        Suspense {
            Content()
        }

        Suspense {
            Footer()
        }
    }
}
