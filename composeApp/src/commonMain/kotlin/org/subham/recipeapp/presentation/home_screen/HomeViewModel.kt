package org.subham.recipeapp.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.subham.recipeapp.domain.repo.RecipeRepository

class HomeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>()
    val uiState = _uiState.asStateFlow()

    init {


    }

    private fun loadRecipeList() {
        viewModelScope.launch {
            val result = recipeRepository.getRecipeList()
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        recipeList = result.getOrDefault(emptyList()),
                        isRecipeListLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isRecipeListLoading = false,
                        recipeListError = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }

}