package org.subham.recipeapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.dsl.module
import org.subham.recipeapp.data.local.DatabaseFactory
import org.subham.recipeapp.di.initKoin

fun main() = application {
    val jvmModules = module {
        single { DatabaseFactory() }
    }
    initKoin(
        additionalModule = listOf(jvmModules)
    ){

    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "RecipeApp",
    ) {
        App()
    }
}