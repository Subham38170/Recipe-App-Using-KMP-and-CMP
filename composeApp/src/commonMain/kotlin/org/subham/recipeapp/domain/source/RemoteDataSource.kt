package org.subham.recipeapp.domain.source

import org.subham.recipeapp.domain.models.RecipeItem

interface RemoteDataSource {

    suspend fun getRecipesList(): List<RecipeItem>
}