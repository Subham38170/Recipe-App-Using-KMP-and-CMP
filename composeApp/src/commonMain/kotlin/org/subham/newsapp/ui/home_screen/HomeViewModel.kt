package org.subham.newsapp.ui.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.data.model.ErrorResponse
import org.subham.newsapp.data.model.NewsResponse
import org.subham.newsapp.data.repository.RemoteNewsRepository
import org.subham.newsapp.utils.Resource

class HomeViewModel(
    private val remoteNewsRepository: RemoteNewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsState = _newsState.asStateFlow()


    var category by mutableStateOf<String?>(null)



    init {
        getHeadlines()
    }

    fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsState.emit(Resource.Loading)
            Logger.d(tag = "RESPONSE", messageString = "Loading")

            try {
                val httpResonse = remoteNewsRepository.getNews()
                if (httpResonse.status.value in 200..209) {
                    val body = httpResonse.body<NewsResponse>()
                    Logger.d(tag = "RESPONSE", messageString = body.toString())
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


    fun getCategoryHeadlines(){

        viewModelScope.launch(Dispatchers.IO) {
            _newsState.emit(Resource.Loading)
            Logger.d(tag = "RESPONSE", messageString = "Loading")

            try {
                val httpResonse = remoteNewsRepository.getCategoryWiseNews(category?: "")
                if (httpResonse.status.value in 200..209) {
                    val body = httpResonse.body<NewsResponse>()
                    Logger.d(tag = "RESPONSE", messageString = body.toString())
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