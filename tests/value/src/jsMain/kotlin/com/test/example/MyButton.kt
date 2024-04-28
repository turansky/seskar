package com.test.example

import seskar.js.JsMixin

open external class HTMLElement
open external class Window

@Suppress("MANY_CLASSES_IN_SUPERTYPE_LIST")
external class MyButton : HTMLElement, @JsMixin Window
