package org.subham.newsapp.ui.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.subham.newsapp.data.repository.RemoteNewsRepository
import org.subham.newsapp.theme.mediumPadding
import org.subham.newsapp.ui.common.ArticleListScreen
import org.subham.newsapp.ui.common.EmptyContent
import org.subham.newsapp.ui.common.SearchBar
import org.subham.newsapp.ui.common.ShimmerEffect
import org.subham.newsapp.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigateTo: (Routes) -> Unit
) {
    val searchViewModel = viewModel { SearchViewModel(RemoteNewsRepository()) }
    val uiState by searchViewModel.newsState.collectAsState()
    var text by rememberSaveable {
        mutableStateOf("")
    }
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


                )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            SearchBar(
                text = text,
                onValueChange = { text = it },
                onSearch = { query ->
                    if (query.trim().isNotEmpty()) searchViewModel.searchNews(query)
                }
            )


            uiState.DisplayResult(
                onIdle = {
                    EmptyContent("Type to search")

                },
                onLoading = {
                    ShimmerEffect()
                },
                onSuccess = {
                    if (it.isEmpty()) EmptyContent("No news")
                    else ArticleListScreen(
                        articles = it,
                        onClick = {
                            navigateTo(Routes.ArticleDetailsScreen(it))
                        }
                    )
                },
                onError = {
                    EmptyContent(it)
                }
            )

        }
    }
}