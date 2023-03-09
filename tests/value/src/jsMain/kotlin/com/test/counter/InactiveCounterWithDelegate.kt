package com.test.counter

import react.VFC

val InactiveCounterWithDelegate = VFC {
    CounterWithDelegate {
        active = false
    }
}
