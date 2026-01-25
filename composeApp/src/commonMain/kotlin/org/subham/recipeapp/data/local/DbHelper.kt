package org.subham.recipeapp.data.local


import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.subham.recipeapp.Recipe
import org.subham.recipeapp.RecipeAppDB

class DbHelper(
    private val driverFactory: DatabaseFactory
) {
    private var db: RecipeAppDB? = null
    private val mutex = Mutex()

    suspend fun <Result : Any> withDatabase(
        block: suspend (RecipeAppDB) -> Result?
    ): Result? = mutex.withLock {

        db?.let {
            db = createDb(driverFactory)
        }
        return@withLock block(db!!)
    }

    private suspend fun createDb(
        driverFactory: DatabaseFactory
    ): RecipeAppDB {
        return RecipeAppDB(
            driver = driverFactory.createDriver(),
            RecipeAdapter = Recipe.Adapter(
                ingredientsAdapter = listOfStringAdapter,
                instructionsAdapter = listOfStringAdapter
            )
        )
    }
}