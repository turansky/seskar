package com.test.workers

import web.console.console
import web.events.iterator
import web.workers.messageEvent

suspend fun main() {
    val worker = createHelloWorker()

    console.log("App started!")

    for (event in worker.messageEvent) {
        console.log("Message:" + event.data)
    }
}
