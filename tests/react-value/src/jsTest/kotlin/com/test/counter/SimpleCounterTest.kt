package com.test.counter

import kotlinx.coroutines.test.TestResult
import react.dom.test.createRoot
import react.dom.test.runReactTest
import react.dom.test.unmount
import web.html.HtmlTagName.button
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleCounterTest {
    @Test
    fun initial(): TestResult = runReactTest { container ->
        val buttons = container.getElementsByTagName(button)

        assertEquals(0, buttons.length, "Buttons count before create")

        val root = createRoot(container, ActiveCounter)

        assertEquals(2, buttons.length, "Buttons count")

        unmount(root)

        assertEquals(0, buttons.length, "Buttons count after unmount")
    }
}
