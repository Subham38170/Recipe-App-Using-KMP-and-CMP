package org.subham.recipeapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module
import org.subham.recipeapp.di.initKoin

class MainApplication : Application() {

    private val androidModule = module {

    }

    override fun onCreate() {
        super.onCreate()

        initKoin(
            additionalModule = listOf(androidModule)
        ) {
            androidContext(applicationContext)
            androidLogger()
        }
    }
}