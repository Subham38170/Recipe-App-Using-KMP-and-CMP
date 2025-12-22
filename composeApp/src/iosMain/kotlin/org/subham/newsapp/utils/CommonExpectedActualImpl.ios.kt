package org.subham.newsapp.utils


import platform.Foundation.NSUUID


actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return  NSUUID().UUIDString()

}