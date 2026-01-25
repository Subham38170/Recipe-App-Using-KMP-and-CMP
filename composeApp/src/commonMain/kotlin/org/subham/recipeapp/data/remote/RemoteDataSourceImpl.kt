package org.subham.recipeapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.subham.recipeapp.data.mappers.toRecipeItem
import org.subham.recipeapp.data.remote.dto.RecipeApiResponse
import org.subham.recipeapp.domain.models.RecipeItem
import org.subham.recipeapp.domain.source.RemoteDataSource

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun getRecipesList(): List<RecipeItem> {
        val httpResponse = httpClient.get("${BASE_URL}search.php?f=b")
        val recipeList = httpResponse.body<RecipeApiResponse>()

        return recipeList.meals.mapNotNull { it?.toRecipeItem() }
    }
}