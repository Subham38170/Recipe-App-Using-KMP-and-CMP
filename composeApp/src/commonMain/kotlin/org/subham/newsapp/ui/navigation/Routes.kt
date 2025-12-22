package org.subham.newsapp.ui.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object HomeScreen: Routes()

    @Serializable
    object SearchScreen: Routes()

    @Serializable
    object FavoriteScreen: Routes()
}

