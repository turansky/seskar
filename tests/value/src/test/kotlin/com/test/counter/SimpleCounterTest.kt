package com.test.counter

import com.test.react.createRoot
import com.test.react.runReactTest
import com.test.react.unmount
import kotlinx.coroutines.test.TestResult
import web.html.HTML
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleCounterTest {
    @Test
    fun initial(): TestResult = runReactTest { container ->
        val buttons = container.getElementsByTagName(HTML.button.toString() /* TEMP */)

        assertEquals(0, buttons.length, "Buttons count before create")

        val root = createRoot(container, Counter)

        assertEquals(2, buttons.length, "Buttons count")

        unmount(root)

        assertEquals(0, buttons.length, "Buttons count after unmount")
    }
}
