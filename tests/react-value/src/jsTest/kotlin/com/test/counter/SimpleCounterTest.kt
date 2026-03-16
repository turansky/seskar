package com.test.counter

import js.test.runJsTest
import react.create
import testing.library.react.cleanup
import testing.library.react.render
import web.dom.document
import web.html.HtmlTagName.button
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleCounterTest {
    @AfterTest
    fun afterTest() {
        cleanup()
    }

    @Test
    fun initial() = runJsTest {
        val buttons = document.body.getElementsByTagName(button)

        assertEquals(0, buttons.length, "Buttons count before create")

        val result = render(ActiveCounter.create())

        assertEquals(2, buttons.length, "Buttons count")

        result.unmount()

        assertEquals(0, buttons.length, "Buttons count after unmount")
    }
}
