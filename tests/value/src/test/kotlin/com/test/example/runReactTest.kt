package com.test.example

import kotlinx.coroutines.test.runTest
import react.VFC
import react.create
import react.dom.client.createRoot
import web.dom.document
import web.html.HTML.div
import web.html.HTMLElement

fun runReactTest(
    testBody: suspend (container: HTMLElement) -> Unit,
) {
    runTest {
        val container = document.createElement(div)
        document.body.appendChild(container)

        testBody(container)
    }
}

fun runReactTest(
    component: VFC,
    testBody: suspend (container: HTMLElement) -> Unit,
) {
    runReactTest { container ->
        val root = act {
            createRoot(container)
                .also { it.render(component.create()) }
        }

        testBody(container)

        act {
            root.unmount()
        }
    }
}
