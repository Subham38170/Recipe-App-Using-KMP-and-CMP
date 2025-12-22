package org.subham.newsapp.ui.navigation

import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.ic_bookmark_filled
import newsapp.composeapp.generated.resources.ic_headline
import newsapp.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource

enum class BottomNavigationItem(
    val icon: DrawableResource,
    val title: String,
    val route: Routes
) {
    HOME(Res.drawable.ic_headline, "Headline", Routes.HomeScreen),
    SEARCH(Res.drawable.ic_search, "Search", Routes.SearchScreen),
    FAVORITE(Res.drawable.ic_bookmark_filled, "BookMark", Routes.FavoriteScreen)
}