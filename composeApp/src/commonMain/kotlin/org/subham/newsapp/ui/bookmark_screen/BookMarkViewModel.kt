package org.subham.newsapp.ui.bookmark_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.data.repository.LocalRepository
import org.subham.newsapp.utils.Resource

class BookMarkViewModel(
    val localNewsRepository: LocalRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState = _newsState.asStateFlow()

    init {
        getHeadlines()
    }

    fun getHeadlines() {
        viewModelScope.launch(Dispatchers.Default) {
            _newsState.emit(Resource.Loading)
            try {
                localNewsRepository.getArticles()
                    .catch {
                        it.printStackTrace()
                        _newsState.emit(Resource.Error(it.message.toString()))
                    }
                    .collectLatest {
                        _newsState.emit(Resource.Success(it))
                    }
            } catch (e: Exception) {
                _newsState.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}