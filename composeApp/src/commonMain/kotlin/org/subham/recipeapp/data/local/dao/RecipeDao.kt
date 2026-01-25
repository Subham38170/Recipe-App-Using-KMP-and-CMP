package org.subham.recipeapp.data.local.dao

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import org.subham.recipeapp.data.local.DbHelper
import org.subham.recipeapp.data.mappers.RecipeEntityMapper
import org.subham.recipeapp.domain.models.RecipeItem

class RecipeDao(
    private val dbHelper: DbHelper
) {

    suspend fun insertRecipe(
        recipeItem: RecipeItem
    ) {
        dbHelper.withDatabase { db ->
            db.recipeEntityQueries.insertRecipe(
                id = recipeItem.id,
                title = recipeItem.title,
                description = recipeItem.description,
                category = recipeItem.category,
                area = recipeItem.area,
                imageUrl = recipeItem.imageUrl ?: "",
                youtubeLink = recipeItem.youtubeLink ?: "",
                ingredients = recipeItem.ingredients,
                isFavorite = if (recipeItem.isFavorite) 1 else 0,
                rating = recipeItem.rating,
                duration = recipeItem.duration,
                difficulty = recipeItem.difficulty,
                instructions = recipeItem.instructions
            )
        }
    }

    suspend fun insertRecipeList(
        recipes: List<RecipeItem>
    ) {
        dbHelper.withDatabase { db ->
            recipes.forEach { recipeItem ->
                db.recipeEntityQueries.insertRecipe(
                    id = recipeItem.id,
                    title = recipeItem.title,
                    description = recipeItem.description,
                    category = recipeItem.category,
                    area = recipeItem.area,
                    imageUrl = recipeItem.imageUrl ?: "",
                    youtubeLink = recipeItem.youtubeLink ?: "",
                    ingredients = recipeItem.ingredients,
                    isFavorite = if (recipeItem.isFavorite) 1 else 0,
                    rating = recipeItem.rating,
                    duration = recipeItem.duration,
                    difficulty = recipeItem.difficulty,
                    instructions = recipeItem.instructions
                )
            }
        }

    }

    suspend fun getAllRecipes(): List<RecipeItem> {
        return dbHelper.withDatabase { db ->

            db.recipeEntityQueries.selectAllRecipes().awaitAsList().map {
                RecipeEntityMapper(it)
            }
        } ?: emptyList()
    }

    suspend fun getRecipeById(id: Long): RecipeItem? {
        return dbHelper.withDatabase { db ->
            db.recipeEntityQueries.selectRecipeById(id).awaitAsOneOrNull()?.let { recipe ->
                RecipeEntityMapper(recipe)
            }
        }
    }

    suspend fun deleteRecipeById(id: Long) {
        dbHelper.withDatabase { db ->
            db.recipeEntityQueries.deleteRecipeById(id)
        }
    }

}