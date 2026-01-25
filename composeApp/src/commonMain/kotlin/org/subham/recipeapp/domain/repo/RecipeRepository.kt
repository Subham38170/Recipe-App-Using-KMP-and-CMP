package org.subham.recipeapp.domain.repo

import org.subham.recipeapp.domain.models.RecipeItem

interface RecipeRepository {
    suspend fun getRecipeList(): Result<List<RecipeItem>>

    suspend fun refreshRecipes(): Result<List<RecipeItem>>

}