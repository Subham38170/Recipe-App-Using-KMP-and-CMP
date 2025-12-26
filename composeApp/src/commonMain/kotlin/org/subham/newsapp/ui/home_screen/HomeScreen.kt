package org.subham.newsapp.ui.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.setting
import newsapp.composeapp.generated.resources.settings_24
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.subham.newsapp.data.model.Article
import org.subham.newsapp.data.model.Source
import org.subham.newsapp.ui.common.ArticleListScreen
import org.subham.newsapp.ui.common.EmptyContent
import org.subham.newsapp.ui.common.ShimmerEffect

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToSettingScreen: ()-> Unit
) {
    val headLineViewModel = viewModel { HomeViewModel() }
    val uiState by headLineViewModel.newsState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "News",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(
                        onClick = navigateToSettingScreen
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.settings_24),
                            contentDescription = stringResource(Res.string.setting)
                        )
                    }

                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ){
            uiState.DisplayResult(
                onIdle = {

                },
                onLoading = {
                    ShimmerEffect()
                },
                onSuccess = {
                    if (it.isEmpty()) EmptyContent("No news")
                    else ArticleListScreen(
                        articles = it,
                        onClick = {}
                    )
                },
                onError = {
                    EmptyContent(it)
                }
            )
        }
    }
}