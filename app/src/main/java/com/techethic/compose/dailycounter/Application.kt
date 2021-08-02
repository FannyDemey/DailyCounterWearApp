package com.techethic.compose.dailycounter

import android.app.Application
import com.techethic.compose.dailycounter.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application(){

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidContext(this@Application)
            modules(appModule)
        }
    }
}