package org.subham.recipeapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.subham.recipeapp.di.initKoin

fun main() = application {
    initKoin {

    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "RecipeApp",
    ) {
        App()
    }
}