package com.test.counter

import kotlinx.coroutines.test.TestResult
import react.dom.test.runReactTest
import react.dom.test.simulateChange
import web.html.HTML.div
import kotlin.test.Test
import kotlin.test.assertEquals

class InactiveStateCounterTest {
    @Test
    fun initial(): TestResult = runReactTest(InactiveStateCounter) { container ->
        val target = container.getElementsByTagName(div)[0]

        assertEquals(1, target.dataCount, "Count #0")

        target.simulateChange()
        assertEquals(1, target.dataCount, "Count #1")

        target.simulateChange()
        assertEquals(1, target.dataCount, "Count #2")

        target.simulateChange()
        assertEquals(1, target.dataCount, "Count #3")

        target.simulateChange()
        assertEquals(1, target.dataCount, "Count #4")
    }
}
