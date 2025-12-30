package org.subham.newsapp.utils

actual fun getType(): Type {
    return Type.WEB
}

actual fun getRandomId(): String {
    return js("crypto.randomUUID()") as String
}