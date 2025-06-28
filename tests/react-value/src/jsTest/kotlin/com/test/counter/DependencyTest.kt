package com.test.counter

import kotlinx.coroutines.test.TestResult
import react.dom.test.runReactTest
import react.dom.test.simulateChange
import web.html.HtmlTagName.div
import kotlin.test.Test
import kotlin.test.assertEquals

class DependencyTest {
    @Test
    fun initial(): TestResult = runReactTest(InactiveCounter) { container ->
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
    fun initialWithDelegate(): TestResult = runReactTest(InactiveCounterWithDelegate) { container ->
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
