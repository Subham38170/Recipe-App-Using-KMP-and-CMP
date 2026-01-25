package org.subham.recipeapp.domain.models

data class RecipeItem(
    val id: Long,
    val title: String,
    val description: String,
    val category: String,
    val area: String,
    val imageUrl: String? = null,
    val youtubeLink: String? = null,
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val isFavorite: Boolean,
    val rating: Long,
    val duration: String? = "20 Mins",
    val difficulty: String? = "Easy"
)