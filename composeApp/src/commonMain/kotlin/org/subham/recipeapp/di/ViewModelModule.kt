package org.subham.recipeapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.subham.recipeapp.presentation.home_screen.HomeViewModel

fun viewModelModule() = module {
    viewModel { HomeViewModel(get()) }

}