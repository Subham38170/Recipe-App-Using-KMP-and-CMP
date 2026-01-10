package org.subham.newsapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.subham.newsapp.data.model.Article


@Dao
interface NewsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upssert(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * from Article where title = :articleId")
    suspend fun getArticle(articleId: String): Article?

    @Delete
    suspend fun delete(article: Article)

    @Query("Delete from article")
    suspend fun deleteALlArticles()
}