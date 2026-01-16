package org.subham.recipeapp.data.local

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.subham.recipeapp.RecipeAppDB

actual class DatabaseFactory(
    private val context: Context
){
    actual suspend fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = RecipeAppDB.Schema.synchronous(),
            context = context
        )
    }
}