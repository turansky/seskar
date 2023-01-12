package com.test.example

fun main() {
    val age0 = Age(42)
    val age1: Any = age0
    val age2: dynamic = age0

    println(age0)
    println(age1)
    println(age2)

    useCustomMemo(age0, age1)

    val city0 = City("Amsterdam")
    val city1: Any = city0
    val city2: dynamic = city0

    useCustomMemo(city0, city1)

    println(city0)
    println(city1)
    println(city2)
}
