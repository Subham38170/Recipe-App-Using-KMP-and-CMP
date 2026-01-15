package org.subham.recipeapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.subham.recipeapp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin {

    }
    ComposeViewport {
        App()
    }
}