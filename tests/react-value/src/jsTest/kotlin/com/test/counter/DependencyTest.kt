package com.test.counter

import react.dom.test.simulateChange
import testing.library.react.cleanup
import testing.library.react.runReactTest
import web.html.HtmlTagName.div
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DependencyTest {
    @AfterTest
    fun afterTest() {
        cleanup()
    }

    @Test
    fun initial() = runReactTest(InactiveCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #1")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #2")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #3")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #4")
    }

    @Test
    fun initialWithDelegate() = runReactTest(InactiveCounterWithDelegate) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #1")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #2")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #3")

        target.simulateChange()
        assertEquals(0, target.dataCount, "Count #4")
    }
}
