package org.subham.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.subham.newsapp.data.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(), DB {
    abstract fun getDao(): NewsDao

    override fun cleanAllTable() {
        super.cleanAllTable()
    }

}

interface DB {
    fun cleanAllTable(): Unit {}
}