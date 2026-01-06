package org.subham.newsapp.utils


import androidx.room.Room
import androidx.room.RoomDatabase
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import io.ktor.util.reflect.instanceOf
import org.subham.newsapp.data.database.NewsDatabase
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import kotlin.reflect.findAssociatedObject
import data.database.instantiateImpl

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

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_NAME"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory = {  NewsDatabase::class.instantiateImpl() }
    )
}