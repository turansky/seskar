package com.test.serviceworker

import web.console.console
import web.events.invoke
import web.events.subscribe
import web.navigator.navigator
import web.notifications.Notification
import web.notifications.NotificationPermission
import web.notifications.granted
import web.notifications.requestPermission
import web.serviceworker.RegistrationOptions
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
        options = RegistrationOptions(type = WorkerType.module),
    )

    val serviceWorker = registration.active
        ?: error("Service worker is not active!")

    serviceWorker.errorEvent().subscribe { event ->
        console.log("Error:")
        console.log(event)
    }

    window.messageEvent().subscribe { event ->
        console.log("Message:")
        console.log(event.data)
    }

    if (Notification.requestPermission() == NotificationPermission.granted) {
        registration.showNotification("Service worker installed!")
    } else {
        console.log("Notification permission denied!")
    }
}
