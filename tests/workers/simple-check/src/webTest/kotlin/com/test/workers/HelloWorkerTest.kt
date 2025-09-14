package com.test.workers

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import web.events.invoke
import web.workers.Worker
import web.workers.invoke
import web.workers.messageEvent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class HelloWorkerTest {
    @Test
    fun create() = runTest {
        val worker = createHelloWorker()

        assertIs<Worker>(worker)

        val data = worker.messageEvent().first().data

        assertEquals("Hello!".toJsString(), data)
    }
}
