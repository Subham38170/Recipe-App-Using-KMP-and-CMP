package org.subham.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.logo
import newsapp.composeapp.generated.resources.welcome
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.subham.newsapp.theme.NewsAppTheme
import org.subham.newsapp.ui.MainScreen
import org.subham.newsapp.utils.getRandomId
import org.subham.newsapp.utils.getType

@Composable
@Preview
fun App() {
    NewsAppTheme {
        MainScreen()
    }
}
