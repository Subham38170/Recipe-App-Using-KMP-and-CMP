package org.subham.recipeapp.domain.source

import org.subham.recipeapp.domain.models.RecipeItem

interface LocalDataSource {
    suspend fun getRecipeItemList(): List<RecipeItem>
    suspend fun saveRecipesList(recipes: List<RecipeItem>)
}