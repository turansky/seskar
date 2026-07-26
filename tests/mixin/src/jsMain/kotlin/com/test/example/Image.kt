package com.test.example

import js.mixin.JsMixin

abstract external class HTMLElement
abstract external class Window

@Suppress("MANY_CLASSES_IN_SUPERTYPE_LIST")
external class Image : HTMLElement, @JsMixin Window
