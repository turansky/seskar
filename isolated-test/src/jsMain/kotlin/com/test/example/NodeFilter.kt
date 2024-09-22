package com.test.example

sealed external interface NodeFilter {
    companion object {
        val FILTER_ACCEPT: NodeFilter
    }
}
