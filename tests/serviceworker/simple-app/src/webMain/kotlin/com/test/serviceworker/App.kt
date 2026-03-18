package com.test.serviceworker

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import web.console.console
import web.events.invoke
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

suspend fun main(): Unit = coroutineScope {
    console.log("App started!")

    val registration = navigator.serviceWorker.register(
        module = MyServiceWorkerModule,
        options = RegistrationOptions(type = WorkerType.module),
    )

    val serviceWorker = registration.active
        ?: error("Service worker is not active!")

    launch {
        serviceWorker.errorEvent().collect { event ->
            console.log("Error:")
            console.log(event)
        }
    }

    launch {
        window.messageEvent().collect { event ->
            console.log("Message:")
            console.log(event.data)
        }
    }

    if (Notification.requestPermission() == NotificationPermission.granted) {
        registration.showNotification("Service worker installed!")
    } else {
        console.log("Notification permission denied!")
    }
}
