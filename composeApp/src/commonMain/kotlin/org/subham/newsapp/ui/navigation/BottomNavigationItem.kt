package org.subham.newsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ViewHeadline
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavigationItem(
    val icon: ImageVector,
    val title: String,
    val route: Routes
) {
    HOME(Icons.Default.ViewHeadline, "Headline", Routes.HomeScreen),
    SEARCH(Icons.Default.Search, "Search", Routes.SearchScreen),
    FAVORITE(Icons.Default.Bookmark, "BookMark", Routes.BookMarkScreen)
}