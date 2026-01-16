package org.subham.recipeapp

import org.koin.dsl.module
import org.subham.recipeapp.data.local.DatabaseFactory
import org.subham.recipeapp.di.initKoin

val iosModules = module {
    single { DatabaseFactory() }
}

fun initKoinIos() =
    initKoin(
        additionalModule = listOf(iosModules)
    )
