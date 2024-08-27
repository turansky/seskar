@file:JsModule("./my-fancy-child-tuple.mjs")

package com.test.example

import seskar.js.JsAlias

sealed external interface MyFancyChildTuple<A : Any, B : Any, C : Any, D : Any> :
    MyFancyTuple<A, B, C> {
    @JsAlias("[3]")
    operator fun component4(): D
}

external fun <A : Any, B : Any, C : Any, D : Any> MyFancyChildTuple(
    a: A,
    b: B,
    c: C,
    d: D,
): MyFancyChildTuple<A, B, C, D>
