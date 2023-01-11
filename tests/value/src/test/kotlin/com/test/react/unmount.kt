package com.test.react

import react.dom.client.Root
import react.dom.test.utils.act

suspend fun unmount(
    root: Root,
) {
    act {
        root.unmount()
    }
}
