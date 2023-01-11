package com.test.react

import react.dom.client.Root

suspend fun unmount(
    root: Root,
) {
    act {
        root.unmount()
    }
}
