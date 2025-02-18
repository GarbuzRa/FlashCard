package com.example.flashcard.app

import android.app.Application
import android.media.tv.interactive.AppLinkInfo
import com.example.flashcard.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App: Application(), KoinComponent  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}