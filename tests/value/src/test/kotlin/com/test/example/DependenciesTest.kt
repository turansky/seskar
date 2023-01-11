package com.test.example

import web.html.HTML
import kotlin.test.Test
import kotlin.test.expect

class DependenciesTest {
    @Test
    fun initial() = runReactTest(Counter) { container ->
        val buttons = container.getElementsByTagName(HTML.button.toString() /* TEMP */)

        expect(2, "Buttons count") {
            buttons.length
        }
    }
}
