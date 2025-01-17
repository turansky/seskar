package com.test.workers

import web.console.console
import web.events.subscribe

suspend fun main() {
    val worker = createHelloWorker()

    console.log("App started!")

    worker.errorEvent.subscribe { event ->
        console.log("Error:", event)
    }

    worker.messageEvent.subscribe { event ->
        console.log("Message:", event.data)
    }
}
