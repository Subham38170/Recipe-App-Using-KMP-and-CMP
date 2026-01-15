package org.subham.recipeapp

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoinIos()
    }
){ App() }