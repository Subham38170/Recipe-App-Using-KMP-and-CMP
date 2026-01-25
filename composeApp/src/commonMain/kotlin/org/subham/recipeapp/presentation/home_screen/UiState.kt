package org.subham.recipeapp.presentation.home_screen

import org.subham.recipeapp.domain.models.RecipeItem

data class UiState(
    val recipeList: List<RecipeItem> = emptyList(),
    val isRecipeListLoading: Boolean = true,
    val recipeListError: String? = null
)