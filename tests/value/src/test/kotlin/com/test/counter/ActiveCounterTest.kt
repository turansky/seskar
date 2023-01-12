package com.test.counter

import js.core.get
import kotlinx.coroutines.test.TestResult
import react.dom.test.Simulate
import react.dom.test.act
import react.dom.test.runReactTest
import web.html.HTML.div
import kotlin.test.Test
import kotlin.test.assertEquals

class ActiveCounterTest {
    @Test
    fun initial(): TestResult = runReactTest(ActiveCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(0, target.dataCount, "Count #0")

        act {
            Simulate.change(target)
        }
        assertEquals(1, target.dataCount, "Count #1")

        act {
            Simulate.change(target)
        }
        assertEquals(2, target.dataCount, "Count #2")

        act {
            Simulate.change(target)
        }
        assertEquals(3, target.dataCount, "Count #3")

        act {
            Simulate.change(target)
        }
        assertEquals(4, target.dataCount, "Count #4")
    }
}
