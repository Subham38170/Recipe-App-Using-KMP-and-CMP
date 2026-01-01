package org.subham.newsapp.utils


import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication


actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return NSUUID().UUIDString()
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

actual fun createSettings(): Settings {
    val delegate = NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(delegate = delegate)
}