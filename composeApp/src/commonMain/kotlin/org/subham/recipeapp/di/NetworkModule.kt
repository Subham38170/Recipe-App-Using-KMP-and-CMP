package org.subham.recipeapp.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import org.subham.recipeapp.data.remote.httpClient

fun networkModule() = module {
    single<HttpClient>{ httpClient }
}