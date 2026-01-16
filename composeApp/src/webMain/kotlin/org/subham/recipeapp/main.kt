package org.subham.recipeapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.koin.dsl.module
import org.subham.recipeapp.data.local.DatabaseFactory
import org.subham.recipeapp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val jsModules = module {
        single { DatabaseFactory() }
    }
    initKoin(
        additionalModule = listOf(jsModules)
    ){

    }
    ComposeViewport {
        App()
    }
}