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
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.ic_browser
import newsapp.composeapp.generated.resources.ic_network_error
import newsapp.composeapp.generated.resources.ic_retry
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
    var query by rememberSaveable {
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
                text = query,
                onValueChange = { query = it },
                onSearch = { query ->
                    if (query.trim().isNotEmpty()) searchViewModel.searchNews(query)
                }
            )


            uiState.DisplayResult(
                onIdle = {
                    EmptyContent(
                        message = "Type to Search",
                        icon = Res.drawable.ic_browser,
                        isOnRetryBtnVisible = false
                    )

                },
                onLoading = {
                    ShimmerEffect()
                },
                onSuccess = {
                    if (it.isEmpty()) {
                        EmptyContent(
                            message = "No news",
                            icon = Res.drawable.ic_retry,
                            isOnRetryBtnVisible = false
                        )
                    } else ArticleListScreen(
                        articles = it,
                        onClick = {
                            navigateTo(Routes.ArticleDetailsScreen(it))
                        }
                    )
                },
                onError = {
                    EmptyContent(
                        message = it,
                        icon = Res.drawable.ic_network_error,
                        onRetryClick = {
                            if (query.trim().isNotEmpty()) searchViewModel.searchNews(query)
                        }
                    )
                }
            )

        }
    }
}