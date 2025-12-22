package org.subham.newsapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.jetbrains.compose.resources.painterResource
import org.subham.newsapp.ui.bookmark_screen.BookMarkScreen
import org.subham.newsapp.ui.home_screen.HomeScreen
import org.subham.newsapp.ui.search_screen.SearchScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier
) {

    val backStack = remember { mutableStateListOf<Routes>(Routes.HomeScreen) }
    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            BottomNavigationItem.entries.forEach {
                item(
                    selected = backStack.last() == it.route,
                    onClick = {
                        if (backStack.last() != it.route) {
                            backStack.clear()
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
                            painter = painterResource(it.icon),
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
                    HomeScreen()
                }
                entry<Routes.SearchScreen> {
                    SearchScreen()
                }
                entry<Routes.BookMarkScreen> {
                    BookMarkScreen()
                }
            }
        )
    }

}