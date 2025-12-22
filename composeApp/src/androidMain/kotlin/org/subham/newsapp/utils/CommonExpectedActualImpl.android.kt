package org.subham.newsapp.utils

import java.util.UUID

actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}