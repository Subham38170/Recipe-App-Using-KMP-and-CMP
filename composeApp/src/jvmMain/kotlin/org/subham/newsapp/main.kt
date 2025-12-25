package org.subham.newsapp

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import coil3.size.Dimension

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "News App",
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        )
    ) {
            App()
    }
}