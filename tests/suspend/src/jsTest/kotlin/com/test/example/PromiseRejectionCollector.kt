package com.test.example

import js.errors.JsError

class PromiseRejectionCollector {
    private var errors: MutableList<JsError>? = mutableListOf()

    fun leave(): List<JsError> {
        PromiseRejectionHandler.instance = null
        val result = requireNotNull(errors).toList()
        errors = null
        return result
    }

    init {
        PromiseRejectionHandler.instance = { error ->
            requireNotNull(errors) += error
        }
    }
}

@JsName("Promise")
private external object PromiseRejectionHandler {
    @JsName("globalRejectionHandler")
    var instance: ((JsError) -> Unit)?
}
