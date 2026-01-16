package org.subham.recipeapp.data.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseFactory {
    suspend fun createDriver(): SqlDriver
}