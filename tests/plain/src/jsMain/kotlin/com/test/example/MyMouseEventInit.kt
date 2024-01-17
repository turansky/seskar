package com.test.example

external interface MyMouseEventInit : MyEventInit {
    val clientX: Int?
    val clientY: Int?
    val movementX: Double?
    val movementY: Double?
    val screenX: Int?
    val screenY: Int?
}
