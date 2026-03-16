package com.test.counter

import testing.library.dom.fireEvent
import testing.library.react.runReactTest
import web.dom.changeEvent
import web.html.HtmlTagName.div
import kotlin.test.Test
import kotlin.test.assertEquals

class ActiveCounterTest {
    @Test
    fun initial() = runReactTest(ActiveCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        fireEvent(target.changeEvent)
        assertEquals(1, target.dataCount, "Count #1")

        fireEvent(target.changeEvent)
        assertEquals(2, target.dataCount, "Count #2")

        fireEvent(target.changeEvent)
        assertEquals(3, target.dataCount, "Count #3")

        fireEvent(target.changeEvent)
        assertEquals(4, target.dataCount, "Count #4")
    }

    @Test
    fun initialWithDelegate() = runReactTest(ActiveCounterWithDelegate) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        fireEvent(target.changeEvent)
        assertEquals(1, target.dataCount, "Count #1")

        fireEvent(target.changeEvent)
        assertEquals(2, target.dataCount, "Count #2")

        fireEvent(target.changeEvent)
        assertEquals(3, target.dataCount, "Count #3")

        fireEvent(target.changeEvent)
        assertEquals(4, target.dataCount, "Count #4")
    }
}
