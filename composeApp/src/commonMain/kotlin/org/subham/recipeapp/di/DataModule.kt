package org.subham.recipeapp.di

import org.koin.dsl.module
import org.subham.recipeapp.data.local.LocalDataSourceImpl
import org.subham.recipeapp.data.remote.RemoteDataSourceImpl
import org.subham.recipeapp.data.repo.RecipeRepositoryImpl
import org.subham.recipeapp.domain.repo.RecipeRepository
import org.subham.recipeapp.domain.source.LocalDataSource
import org.subham.recipeapp.domain.source.RemoteDataSource

fun dataModule() = module {
    single<LocalDataSource> { LocalDataSourceImpl(get()) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single<RecipeRepository> { RecipeRepositoryImpl(get(), get()) }
}