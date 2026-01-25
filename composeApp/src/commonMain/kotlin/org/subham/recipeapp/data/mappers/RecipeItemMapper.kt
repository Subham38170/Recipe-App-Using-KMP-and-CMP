package org.subham.recipeapp.data.mappers

import org.subham.recipeapp.Recipe
import org.subham.recipeapp.data.remote.dto.RecipeItemDto
import org.subham.recipeapp.domain.models.RecipeItem

fun RecipeItemDto.toRecipeItem(): RecipeItem? {
    return if (idMeal != null)
        RecipeItem(
            id = idMeal.toLong(),
            title = strMeal ?: "",
            description = strMeal ?: "",
            category = strCategory ?: "",
            area = strArea ?: "",
            imageUrl = strMealThumb,
            youtubeLink = strYoutube,
            ingredients = listOf(
                "$strIngredient1:$strMeasure1",
                "$strIngredient2:$strMeasure2",
                "$strIngredient3:$strMeasure3",
                "$strIngredient4:$strMeasure4",
                "$strIngredient5:$strMeasure5",
                "$strIngredient6:$strMeasure6",
                "$strIngredient7:$strMeasure7",
                "$strIngredient8:$strMeasure8",
                "$strIngredient9:$strMeasure9",
                "$strIngredient10:$strMeasure10",
                "$strIngredient11:$strMeasure11",
                "$strIngredient12:$strMeasure12",
                "$strIngredient13:$strMeasure13",
                "$strIngredient14:$strMeasure14",
                "$strIngredient15:$strMeasure15",
                "$strIngredient16:$strMeasure16",
                "$strIngredient17:$strMeasure17",
                "$strIngredient18:$strMeasure18",
                "$strIngredient19:$strMeasure19",
                "$strIngredient20:$strMeasure20"
            ),
            instructions = strInstructions?.split(" ")
                ?.map { it.trim().replace("\r\n", "").capitalizeFirstWord() }
                ?.filter { it.isNotEmpty() } ?: emptyList(),
            isFavorite = false,
            rating = 3
        )
    else null
}

fun String.capitalizeFirstWord() = this.replaceFirstChar { it.uppercase() }

fun RecipeEntityMapper(
    recipe: Recipe
) = RecipeItem(
    id = recipe.id,
    title = recipe.title,
    description = recipe.description,
    category = recipe.category,
    area = recipe.area,
    imageUrl = recipe.imageUrl,
    youtubeLink = recipe.youtubeLink,
    ingredients = recipe.ingredients,
    instructions = recipe.instructions,
    isFavorite = if (recipe.isFavorite == 0L) true else false,
    rating = recipe.rating,
    duration = recipe.duration,
    difficulty = recipe.difficulty
)