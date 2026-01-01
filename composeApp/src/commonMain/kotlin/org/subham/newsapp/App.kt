package org.subham.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.subham.newsapp.theme.NewsAppTheme
import org.subham.newsapp.ui.navigation.NavGraph
import org.subham.newsapp.ui.setting_screen.SettingViewModel
import org.subham.newsapp.utils.AppPreferences
import org.subham.newsapp.utils.createSettings

@Composable
@Preview
fun App() {
    val appPreferences = remember { AppPreferences(createSettings()) }

    val settingViewModel = viewModel { SettingViewModel(appPreferences) }
    val currentTheme by settingViewModel.currentTheme.collectAsStateWithLifecycle()
    NewsAppTheme(
        appTheme = currentTheme
    ) {
        NavGraph(
            settingViewModel = settingViewModel
        )
    }
}
