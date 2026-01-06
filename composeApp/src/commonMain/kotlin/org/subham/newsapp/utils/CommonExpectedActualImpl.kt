package org.subham.newsapp.utils

import androidx.room.RoomDatabase
import com.russhwolf.settings.Settings
import org.subham.newsapp.data.database.NewsDatabase


expect fun getType(): Type


expect fun getRandomId(): String


expect fun sharedLink(url: String)


expect fun createSettings(): Settings

expect fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>
