package com.test.counter

import com.test.react.runReactTest
import js.core.get
import kotlinx.coroutines.test.TestResult
import react.dom.test.act
import web.dom.CHANGE
import web.events.Event
import web.html.HTML.div
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class ActiveCounterTest {
    @Test
    @Ignore
    fun initial(): TestResult = runReactTest(ActiveCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(1, target.dataCount, "Count #1")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(2, target.dataCount, "Count #2")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(3, target.dataCount, "Count #3")

        act {
            target.dispatchEvent(Event(Event.CHANGE))
        }
        assertEquals(4, target.dataCount, "Count #4")
    }
}
