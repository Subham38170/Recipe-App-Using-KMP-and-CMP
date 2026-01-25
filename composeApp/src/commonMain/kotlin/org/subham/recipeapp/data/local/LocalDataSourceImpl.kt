package org.subham.recipeapp.data.local

import org.subham.recipeapp.data.local.dao.RecipeDao
import org.subham.recipeapp.domain.models.RecipeItem
import org.subham.recipeapp.domain.source.LocalDataSource

class LocalDataSourceImpl(
    private val recipeDao: RecipeDao
) : LocalDataSource {
    override suspend fun getRecipeItemList(): List<RecipeItem> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun saveRecipesList(recipes: List<RecipeItem>) {
        recipeDao.insertRecipeList(recipes)
    }
}