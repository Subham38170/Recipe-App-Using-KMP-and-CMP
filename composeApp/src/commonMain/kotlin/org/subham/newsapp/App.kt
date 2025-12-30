package org.subham.newsapp

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.subham.newsapp.theme.NewsAppTheme
import org.subham.newsapp.ui.MainScreen

@Composable
@Preview
fun App() {
    NewsAppTheme {
        MainScreen()
    }
}
