package org.subham.newsapp.utils

import kotlinx.browser.window
import kotlin.js.js

actual fun getType(): Type {
    return Type.WEB
}

actual fun getRandomId(): String {
    return js("crypto.randomUUID()") as String
}

actual fun sharedLink(url: String) {
    window.open(url, "_blank")

}