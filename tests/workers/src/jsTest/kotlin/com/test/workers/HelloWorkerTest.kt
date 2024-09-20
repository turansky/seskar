package com.test.workers

import kotlinx.coroutines.test.runTest
import web.events.once
import web.workers.messageEvent
import kotlin.test.assertEquals

class HelloWorkerTest {
    // @Test
    fun create() = runTest {
        val worker = createHelloWorker()

        val data = worker.messageEvent.once().data

        assertEquals("Hello!", data)
    }
}
