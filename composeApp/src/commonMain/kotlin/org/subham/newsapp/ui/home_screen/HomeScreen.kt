package org.subham.newsapp.ui.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import newsapp.composeapp.generated.resources.ic_browser
import newsapp.composeapp.generated.resources.ic_network_error
import newsapp.composeapp.generated.resources.ic_retry
import newsapp.composeapp.generated.resources.no_news
import newsapp.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource
import org.subham.newsapp.data.repository.RemoteNewsRepository
import org.subham.newsapp.ui.common.ArticleListScreen
import org.subham.newsapp.ui.common.EmptyContent
import org.subham.newsapp.ui.common.ShimmerEffect
import org.subham.newsapp.ui.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateTo: (Routes) -> Unit
) {
    val headLineViewModel = viewModel { HomeViewModel(RemoteNewsRepository()) }
    val uiState by headLineViewModel.newsState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "News",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }, actions = {
                IconButton(
                    onClick = {
                        navigateTo(Routes.SettingScreen)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(Res.string.setting)
                    )
                }

            })
        }) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            uiState.DisplayResult(onIdle = {

            }, onLoading = {
                ShimmerEffect()
            }, onSuccess = {
                if (it.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browser,
                        onRetryClick = {
                            headLineViewModel.getHeadlines()
                        }
                    )
                } else ArticleListScreen(
                    articles = it,
                    onClick = {
                        navigateTo(Routes.ArticleDetailsScreen(it))
                    })
            }, onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = {
                        headLineViewModel.getHeadlines()
                    }
                )
            })
        }
    }
}