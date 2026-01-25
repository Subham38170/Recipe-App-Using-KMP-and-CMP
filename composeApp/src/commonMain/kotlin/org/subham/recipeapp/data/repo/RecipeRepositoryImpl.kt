package org.subham.recipeapp.data.repo

import org.subham.recipeapp.domain.models.RecipeItem
import org.subham.recipeapp.domain.repo.RecipeRepository
import org.subham.recipeapp.domain.source.LocalDataSource
import org.subham.recipeapp.domain.source.RemoteDataSource

class RecipeRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RecipeRepository {
    override suspend fun getRecipeList(): Result<List<RecipeItem>> {
        return try {
            val recipeListCache = localDataSource.getRecipeItemList()
            if (recipeListCache.isNotEmpty()) {
                Result.success(recipeListCache)
            } else {
                val recipeListApiResponse = remoteDataSource.getRecipesList()
                localDataSource.saveRecipesList(recipeListApiResponse)
                Result.success(recipeListApiResponse)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun refreshRecipes(): Result<List<RecipeItem>> {
        return try {
            val recipeListApiResponse = remoteDataSource.getRecipesList()
            localDataSource.saveRecipesList(recipeListApiResponse)
            Result.success(recipeListApiResponse)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}