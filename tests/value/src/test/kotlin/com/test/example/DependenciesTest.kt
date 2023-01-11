package com.test.example

import web.html.HTML
import kotlin.test.Test
import kotlin.test.expect

class DependenciesTest {
    @Test
    fun initial() = runReactTest { container ->
        val buttons = container.getElementsByTagName(HTML.button.toString() /* TEMP */)

        expect(0, "Buttons count before create") {
            buttons.length
        }

        val root = createRoot(container, Counter)

        expect(2, "Buttons count") {
            buttons.length
        }

        unmount(root)

        expect(0, "Buttons count after unmount") {
            buttons.length
        }
    }
}
