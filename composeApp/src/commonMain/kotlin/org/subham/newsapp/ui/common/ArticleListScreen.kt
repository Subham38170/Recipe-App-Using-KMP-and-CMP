package org.subham.newsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.theme.cardMinSize
import org.subham.newsapp.theme.xLargePadding
import org.subham.newsapp.utils.getRandomId

@Composable
fun ArticleListScreen(
    articles: List<Article>,
    onClick: (Article) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(cardMinSize),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding)
    ) {
        items(articles, key = { it.publishedAt + getRandomId() }) { article ->
            ArticleItem(
                article = article,
                onClick = {
                    onClick(article)
                }
            )
        }
    }
}