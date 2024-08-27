@file:JsModule("./my-fancy-tuple.mjs")

package com.test.example

import seskar.js.JsAlias

sealed external interface MyFancyTuple<A : Any, B : Any, C : Any> {
    @JsAlias("[0]")
    operator fun component1(): A

    @JsAlias("[1]")
    operator fun component2(): B

    @JsAlias("[2]")
    operator fun component3(): C
}

external fun <A : Any, B : Any, C : Any> MyFancyTuple(
    a: A,
    b: B,
    c: C,
): MyFancyTuple<A, B, C>
