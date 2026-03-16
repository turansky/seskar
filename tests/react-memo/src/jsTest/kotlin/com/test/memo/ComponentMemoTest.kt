package com.test.memo

import testing.library.dom.fireEvent
import testing.library.react.cleanup
import testing.library.react.runReactTest
import web.dom.changeEvent
import web.dom.clickEvent
import web.html.HtmlTagName.button
import web.html.HtmlTagName.div
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentMemoTest {
    @AfterTest
    fun afterTest() {
        cleanup()
    }

    @Test
    fun initial() = runReactTest(CounterApp) { container ->
        val target = container.getElementsByTagName(div)[0]
        val button = container.getElementsByTagName(button)[0]

        assertEquals(1, target.dataCount, "Count #1.1")
        assertEquals(1, button.dataCount, "Count #1.2")

        fireEvent(target.changeEvent)
        assertEquals(2, target.dataCount, "Count #2.1")
        assertEquals(1, button.dataCount, "Count #2.2")

        fireEvent(target.changeEvent)
        assertEquals(3, target.dataCount, "Count #3.1")
        assertEquals(1, button.dataCount, "Count #3.2")

        fireEvent(button.clickEvent)
        assertEquals(3, target.dataCount, "Count #4.1")
        assertEquals(2, button.dataCount, "Count #4.2")

        fireEvent(button.clickEvent)
        assertEquals(3, target.dataCount, "Count #5.1")
        assertEquals(3, button.dataCount, "Count #5.2")
    }
}
