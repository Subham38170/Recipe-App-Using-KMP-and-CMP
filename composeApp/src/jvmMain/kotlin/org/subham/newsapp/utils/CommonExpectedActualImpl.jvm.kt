package org.subham.newsapp.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.subham.newsapp.data.database.NewsDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.UUID
import java.util.prefs.Preferences

actual fun getType(): Type {
    return Type.DESKTOP
}


actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun sharedLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun createSettings(): Settings {

    val delegate = Preferences.userRoot().node(SharedPrefsFileName)
    return PreferencesSettings(delegate = delegate)
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"),DB_NAME)
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFile.absolutePath
    )

}