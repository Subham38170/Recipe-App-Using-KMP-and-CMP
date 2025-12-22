package org.subham.newsapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.painterResource

@Composable
fun NavGraph(
    modifier: Modifier = Modifier
) {
    var route by remember { mutableStateOf<Routes>(Routes.HomeScreen) }

    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            BottomNavigationItem.entries.forEach {
                item(
                    selected = route == it.route,
                    onClick = {
                        route = it.route
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

    }

}