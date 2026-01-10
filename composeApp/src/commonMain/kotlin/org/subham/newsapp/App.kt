package org.subham.newsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.subham.newsapp.data.repository.LocalRepository
import org.subham.newsapp.theme.NewsAppTheme
import org.subham.newsapp.ui.navigation.NavGraph
import org.subham.newsapp.ui.setting_screen.SettingViewModel
import org.subham.newsapp.utils.AppPreferences
import org.subham.newsapp.utils.createSettings
import org.subham.newsapp.utils.getDatabaseBuilder
import org.subham.newsapp.utils.getRoomDatabase

@Composable
@Preview
fun App() {
    val appPreferences = remember { AppPreferences(createSettings()) }

    val newsDao = remember { getRoomDatabase(getDatabaseBuilder()).getDao() }


    val settingViewModel = viewModel { SettingViewModel(LocalRepository(newsDao),appPreferences) }
    val currentTheme by settingViewModel.currentTheme.collectAsStateWithLifecycle()
    NewsAppTheme(
        appTheme = currentTheme
    ) {
        NavGraph(
            settingViewModel = settingViewModel,
            newsDao = newsDao
        )
    }
}
