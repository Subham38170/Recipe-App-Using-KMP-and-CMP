package org.subham.recipeapp.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.subham.recipeapp.data.local.DbHelper
import org.subham.recipeapp.data.local.dao.RecipeDao
import kotlin.coroutines.CoroutineContext

fun cacheModule() = module {
    single<CoroutineContext>{ Dispatchers.Default }
    single { CoroutineScope(get()) }

    single { DbHelper(get()) }
    single { RecipeDao(get())}
}