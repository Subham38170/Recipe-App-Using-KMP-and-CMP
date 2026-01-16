package org.subham.recipeapp.data.local

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.subham.recipeapp.RecipeAppDB

actual class DatabaseFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(
            JdbcSqliteDriver.IN_MEMORY
        )
        RecipeAppDB.Schema.awaitCreate(driver)
        return driver
    }
}