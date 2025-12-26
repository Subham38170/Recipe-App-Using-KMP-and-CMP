package org.subham.newsapp.ui.bookmark_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.ui.home_screen.articlesList
import org.subham.newsapp.utils.Resource

class BookMarkViewModel : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState = _newsState.asStateFlow()

    init {
        getHeadlines()
    }

    private fun getHeadlines() {
        viewModelScope.launch(Dispatchers.Default) {
            _newsState.emit(Resource.Loading)
            try {

                _newsState.emit(Resource.Success(articlesList))
            } catch (e: Exception) {
                _newsState.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}