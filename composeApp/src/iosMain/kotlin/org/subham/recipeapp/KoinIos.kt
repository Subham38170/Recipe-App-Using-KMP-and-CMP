package org.subham.recipeapp

import org.koin.dsl.module
import org.subham.recipeapp.di.initKoin

val iosModules = module {

}

fun initKoinIos() =
    initKoin(
        additionalModule = listOf(iosModules)
    ) {

    }
