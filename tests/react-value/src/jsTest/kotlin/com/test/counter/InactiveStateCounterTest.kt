package com.test.counter

import testing.library.dom.fireEvent
import testing.library.react.cleanup
import testing.library.react.runReactTest
import web.dom.changeEvent
import web.html.HtmlTagName.div
import kotlin.test.AfterTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class InactiveStateCounterTest {
    @AfterTest
    fun afterTest() {
        cleanup()
    }

    // TODO: fix
    @Ignore
    @Test
    fun initial() = runReactTest(InactiveStateCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(1, target.dataCount, "Count #0")

        fireEvent(target.changeEvent)
        assertEquals(1, target.dataCount, "Count #1")

        fireEvent(target.changeEvent)
        assertEquals(1, target.dataCount, "Count #2")

        fireEvent(target.changeEvent)
        assertEquals(1, target.dataCount, "Count #3")

        fireEvent(target.changeEvent)
        assertEquals(1, target.dataCount, "Count #4")
    }
}
