package org.subham.recipeapp.data.local

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import app.cash.sqldelight.driver.worker.expected.Worker
import org.subham.recipeapp.RecipeAppDB
import kotlin.js.js


actual class DatabaseFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver = createDefaultWebWorkerDriver()
        RecipeAppDB.Schema.awaitCreate(driver)
        return driver
    }

}