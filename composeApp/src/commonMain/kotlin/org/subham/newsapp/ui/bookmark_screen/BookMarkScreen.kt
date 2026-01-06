package org.subham.newsapp.ui.bookmark_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import newsapp.composeapp.generated.resources.ic_browser
import newsapp.composeapp.generated.resources.ic_network_error
import org.subham.newsapp.data.repository.LocalRepository
import org.subham.newsapp.ui.common.ArticleListScreen
import org.subham.newsapp.ui.common.EmptyContent
import org.subham.newsapp.ui.common.ShimmerEffect
import org.subham.newsapp.ui.navigation.Routes
import org.subham.newsapp.utils.getDatabaseBuilder
import org.subham.newsapp.utils.getRoomDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarkScreen(
    navigateTo: (Routes) -> Unit
) {

    val bookMarkViewModel = viewModel {
        BookMarkViewModel(LocalRepository(getRoomDatabase(getDatabaseBuilder()).getDao()))
    }
    val uiState by bookMarkViewModel.newsState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bookmarks",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            uiState.DisplayResult(
                onIdle = {

                },
                onLoading = {
                    ShimmerEffect()
                },
                onSuccess = {
                    if (it.isEmpty()) {
                        EmptyContent(
                            message = "No News",
                            icon = Res.drawable.ic_browser,
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
                        isOnRetryBtnVisible = true,
                        onRetryClick = {
                            bookMarkViewModel.getHeadlines()
                        }
                    )
                }
            )
        }
    }
}