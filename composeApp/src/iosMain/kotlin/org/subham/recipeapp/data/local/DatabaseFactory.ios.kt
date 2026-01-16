package org.subham.recipeapp.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.subham.recipeapp.RecipeAppDB
import org.subham.recipeapp.utils.DB_FILE_NAME

actual class DatabaseFactory {
    actual suspend fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            RecipeAppDB.Schema.synchronous(),
            name = DB_FILE_NAME
        )
    }
}