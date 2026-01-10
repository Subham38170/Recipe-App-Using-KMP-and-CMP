package org.subham.newsapp.ui.setting_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LightMode
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.delete_bookmark
import newsapp.composeapp.generated.resources.settings
import newsapp.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.subham.newsapp.ui.setting_screen.components.DeleteBookmarkDialog
import org.subham.newsapp.ui.setting_screen.components.SettingItem
import org.subham.newsapp.ui.setting_screen.components.ThemeSelectionDialog
import org.subham.newsapp.utils.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    onBackClick: () -> Unit,
    settingViewModel: SettingViewModel
) {
    var showDeleteBookmarkDialog by remember { mutableStateOf(false) }
    var showThemeSelectionDialog by remember { mutableStateOf(false) }

    val currentTheme by settingViewModel.currentTheme.collectAsState()
    when {
        showThemeSelectionDialog -> {
            ThemeSelectionDialog(
                currentTheme = currentTheme ?: Theme.SYSTEM_DEFAULT.name,
                onThemeChange = {
                    settingViewModel.changeTheme(it.name)
                    showThemeSelectionDialog = false
                },
                onDismissRequest = {
                    showThemeSelectionDialog = false
                }
            )
        }

        showDeleteBookmarkDialog -> {
            DeleteBookmarkDialog(
                onDismissRequest = {
                    showDeleteBookmarkDialog = false
                },
                onDeleteBookmark = {
                    settingViewModel.deleteAllBookMarkedArticles()
                    showDeleteBookmarkDialog = false

                }
            )
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.settings)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigation Icon"
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                SettingItem(
                    onClick = {
                        showThemeSelectionDialog = true
                    },
                    imageVector = Icons.Default.LightMode,
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        showDeleteBookmarkDialog = true
                    },
                    imageVector = Icons.Default.Delete,
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error
                )
            }
        }

    }
}