package com.test.serviceworker

import js.objects.unsafeJso
import web.console.console
import web.events.subscribe
import web.navigator.navigator
import web.serviceworker.errorEvent
import web.serviceworker.register
import web.serviceworker.showNotification
import web.window.messageEvent
import web.window.window
import web.workers.WorkerType
import web.workers.module

suspend fun main() {
    console.log("App started!")

    val registration = navigator.serviceWorker.register(
        module = MyServiceWorkerModule,
        options = unsafeJso {
            type = WorkerType.module
        }
    )

    val serviceWorker = registration.active!!
    registration.showNotification("Service worker installed!")

    serviceWorker.errorEvent.subscribe { event ->
        console.log("Error:")
        console.log(event)
    }

    window.messageEvent.subscribe { event ->
        console.log("Message:")
        console.log(event.data)
    }
}
