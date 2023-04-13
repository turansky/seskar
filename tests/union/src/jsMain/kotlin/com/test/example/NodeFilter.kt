@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

external sealed interface NodeFilter {
    companion object {
        val FILTER_ACCEPT: NodeFilter
    }
}
