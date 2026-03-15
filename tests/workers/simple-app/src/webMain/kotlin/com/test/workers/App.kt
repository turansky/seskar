// TEMP
@file:OptIn(kotlin.js.ExperimentalWasmJsInterop::class)

package com.test.workers

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import web.console.console
import web.events.invoke
import web.workers.errorEvent
import web.workers.invoke
import web.workers.messageEvent

suspend fun main(): Unit = coroutineScope {
    val worker = createHelloWorker()

    console.log("App started!")

    launch {
        worker.errorEvent().collect { event ->
            console.log("Error:")
            console.log(event)
        }
    }

    launch {
        worker.messageEvent().collect { event ->
            console.log("Message:")
            console.log(event.data)
        }
    }
}
