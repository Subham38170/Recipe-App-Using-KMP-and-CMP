package org.subham.newsapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn
import org.subham.newsapp.data.database.NewsDao
import org.subham.newsapp.data.model.Article

class LocalRepository(
    private val newsDao: NewsDao
) {
    suspend fun upsertArticle(article: Article) {
        newsDao.upssert(article)
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)

    suspend fun getArticle(articleId: String) = newsDao.getArticle(articleId)

    suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

}