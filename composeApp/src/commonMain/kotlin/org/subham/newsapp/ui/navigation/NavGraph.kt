package org.subham.newsapp.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.subham.newsapp.data.database.NewsDao
import org.subham.newsapp.data.database.NewsDatabase
import org.subham.newsapp.ui.article_details.ArticleDetailScreen
import org.subham.newsapp.ui.bookmark_screen.BookMarkScreen
import org.subham.newsapp.ui.home_screen.HomeScreen
import org.subham.newsapp.ui.search_screen.SearchScreen
import org.subham.newsapp.ui.setting_screen.SettingScreen
import org.subham.newsapp.ui.setting_screen.SettingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel,
    newsDao: NewsDao
) {
    val backStack = remember { mutableStateListOf<Routes>(Routes.HomeScreen) }

    val navigationSuiteScaffoldState = rememberNavigationSuiteScaffoldState()

    val scope = rememberCoroutineScope()

    val showNavigationItems = backStack.last() in BottomNavigationItem.entries.map { it.route }

    LaunchedEffect(showNavigationItems) {
        if (showNavigationItems) navigationSuiteScaffoldState.show()
        else navigationSuiteScaffoldState.hide()
    }
    NavigationSuiteScaffold(
        state = navigationSuiteScaffoldState,
        modifier = modifier,
        navigationSuiteItems = {

            BottomNavigationItem.entries.forEach {
                item(
                    selected = backStack.last() == it.route,
                    onClick = {
                        if (backStack.last() != it.route) {
                            backStack.add(it.route)
                        }
                    },
                    label = {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title

                        )
                    }
                )
            }
        }


    ) {


        NavDisplay(
            backStack = backStack,
            onBack = {
                backStack.removeLastOrNull()
            },
            entryDecorators = listOf(
// Add the default decorators for managing scenes and saving state
                rememberSaveableStateHolderNavEntryDecorator(),
                // Then add the view model store decorator
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Routes.HomeScreen> {
                    HomeScreen(
                        navigateTo = {
                            if (backStack.last() != it) backStack.add(it)
                        }
                    )
                }
                entry<Routes.SearchScreen> {
                    SearchScreen(
                        navigateTo = {
                            if (backStack.last() != it) backStack.add(it)

                        }
                    )
                }
                entry<Routes.BookMarkScreen> {
                    BookMarkScreen(
                        navigateTo = {
                            if (backStack.last() != it) backStack.add(it)

                        }
                    )
                }
                entry<Routes.ArticleDetailsScreen> {
                    ArticleDetailScreen(
                        article = it.article,
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                        newsDao = newsDao
                    )
                }
                entry<Routes.SettingScreen>(
                    metadata = NavDisplay.transitionSpec {
                        slideInHorizontally(
                            initialOffsetX = { it },
                        ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                    } + NavDisplay.popTransitionSpec {
                        // Slide old content down, revealing the new content in place underneath
                        EnterTransition.None togetherWith
                                slideOutHorizontally(
                                    targetOffsetX = { it },
                                )

                    }
                ) {

                    SettingScreen(
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                        settingViewModel = settingViewModel
                    )
                }
            }
        )
    }

}