package com.test.counter

import com.test.react.runReactTest
import js.core.get
import kotlinx.coroutines.test.TestResult
import react.dom.test.act
import web.dom.CHANGE
import web.events.Event
import web.html.HTML.div
import kotlin.test.Test
import kotlin.test.assertEquals

class DependencyTest {
    @Test
    fun initial(): TestResult = runReactTest(InactiveCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(0, target.dataCount, "Count #1")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(0, target.dataCount, "Count #2")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(0, target.dataCount, "Count #3")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(0, target.dataCount, "Count #4")
    }
}
