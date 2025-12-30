package org.subham.newsapp.utils


import platform.Foundation.NSUUID
import platform.Foundation.NSHomeDirectory
import platform.UIKit.*


actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return  NSUUID().UUIDString()
}

actual fun sharedLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}