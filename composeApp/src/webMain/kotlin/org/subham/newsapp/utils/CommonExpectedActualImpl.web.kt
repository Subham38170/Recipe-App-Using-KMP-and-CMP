package org.subham.newsapp.utils

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

actual fun getType(): Type {
    return Type.WEB
}

@OptIn(ExperimentalWasmJsInterop::class)
actual fun getRandomId(): String {
    return js("crypto.randomUUID()") as String
}