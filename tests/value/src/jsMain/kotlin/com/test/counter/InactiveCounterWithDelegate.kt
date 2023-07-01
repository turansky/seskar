package com.test.counter

import react.FC

val InactiveCounterWithDelegate = FC {
    CounterWithDelegate {
        active = false
    }
}
