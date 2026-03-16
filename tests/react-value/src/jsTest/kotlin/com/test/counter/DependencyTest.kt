package com.test.counter

import testing.library.dom.fire
import testing.library.react.cleanup
import testing.library.react.runReactTest
import web.dom.changeEvent
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

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #1")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #2")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #3")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #4")
    }

    @Test
    fun initialWithDelegate() = runReactTest(InactiveCounterWithDelegate) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #1")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #2")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #3")

        target.changeEvent.fire()
        assertEquals(0, target.dataCount, "Count #4")
    }
}
