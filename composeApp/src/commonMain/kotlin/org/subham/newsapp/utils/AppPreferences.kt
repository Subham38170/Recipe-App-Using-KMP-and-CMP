package org.subham.newsapp.utils

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.subham.newsapp.data.database.NewsDatabase
import org.subham.newsapp.data.model.NewsResponse

class AppPreferences(
    private val settings: Settings
) {
    companion object {
        const val THEME_KEY = "Theme"
    }

    fun changeTheme(theme: String) = settings.putString(THEME_KEY, theme)


    fun getTheme(): String {
        val savedTheme = settings.getString(THEME_KEY, Theme.SYSTEM_DEFAULT.name)
        return savedTheme
    }
}


fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

}