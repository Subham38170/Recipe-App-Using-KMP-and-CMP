package org.subham.newsapp.ui.article_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.data.repository.LocalRepository


class ArticleDetailsViewModel(
    private val localNewsRepository: LocalRepository
) : ViewModel() {
    var isBookMarked by mutableStateOf(false)


    fun isArticleBookmarked(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.getArticle(article.title)?.let {
                isBookMarked = true
            }
        }
    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isBookMarked)
                localNewsRepository.upsertArticle(article = currentArticle)
            else localNewsRepository.deleteArticle(currentArticle)
            isBookMarked = !isBookMarked

        }
    }
}