package org.subham.newsapp.ui.home_screen

import androidx.compose.runtime.Composable
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.data.model.Source
import org.subham.newsapp.ui.common.ArticleListScreen

val articlesList = listOf(
    Article(
        author = "John Doe",
        content = "India is seeing rapid growth in the technology sector...",
        description = "India's tech industry continues to expand rapidly.",
        publishedAt = "2025-01-20T10:30:00Z",
        source = Source(
            id = "bbc-news",
            name = "BBC News"
        ),
        title = "India's Tech Boom in 2025",
        url = "https://www.bbc.com/news/technology-india",
        urlToImage = "https://example.com/images/tech_india.jpg"
    ),

    Article(
        author = "Jane Smith",
        content = "Scientists have discovered a new method to store renewable energy...",
        description = "A breakthrough in renewable energy storage.",
        publishedAt = "2025-01-19T08:15:00Z",
        source = Source(
            id = "the-verge",
            name = "The Verge"
        ),
        title = "New Breakthrough in Renewable Energy",
        url = "https://www.theverge.com/renewable-energy",
        urlToImage = "https://example.com/images/renewable.jpg"
    ),

    Article(
        author = null,
        content = "The Indian cricket team secured a thrilling victory...",
        description = "India wins an intense cricket match.",
        publishedAt = "2025-01-18T18:45:00Z",
        source = Source(
            id = "espn-cricinfo",
            name = "ESPN Cricinfo"
        ),
        title = "India Clinches Thriller in Last Over",
        url = "https://www.espncricinfo.com/india-match",
        urlToImage = "https://example.com/images/cricket.jpg"
    )
)

@Composable
fun HomeScreen() {
    ArticleListScreen(
        articles = articlesList,
        onClick = {}
    )
}