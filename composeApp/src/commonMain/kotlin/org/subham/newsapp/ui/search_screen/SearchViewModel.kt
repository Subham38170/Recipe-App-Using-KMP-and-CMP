package org.subham.newsapp.ui.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.data.model.ErrorResponse
import org.subham.newsapp.data.model.NewsResponse
import org.subham.newsapp.data.repository.RemoteNewsRepository
import org.subham.newsapp.utils.Resource

class SearchViewModel(
    private val remoteNewsRepository: RemoteNewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState = _newsState.asStateFlow()



    fun searchNews(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _newsState.emit(Resource.Loading)
            try {
                val httpResonse = remoteNewsRepository.searchNews(query)
                if (httpResonse.status.value in 200..209) {
                    val body = httpResonse.body<NewsResponse>()
                    _newsState.emit(Resource.Success(body.articles))

                } else {
                    val body = httpResonse.body<ErrorResponse>()
                    _newsState.emit(Resource.Error(body.message))
                }

            } catch (e: Exception) {
                _newsState.emit(Resource.Error(e.message.toString()))
            }
        }
    }
}