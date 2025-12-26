package org.subham.newsapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    object HomeScreen : Routes()

    @Serializable
    object SearchScreen : Routes()

    @Serializable
    object BookMarkScreen : Routes()

    @Serializable
    object SettingScreen: Routes()
}

