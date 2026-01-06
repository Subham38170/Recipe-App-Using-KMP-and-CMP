package org.subham.newsapp.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import org.subham.newsapp.data.model.Source

class SourceTypeConverter {

    @TypeConverter
    fun fromSourceToString(
        source: Source
    ): String {
        return Json.encodeToString(source)
    }

    @TypeConverter
    fun stringToSource(
        source: String
    ): Source {
        return Json.decodeFromString(source)
    }
}