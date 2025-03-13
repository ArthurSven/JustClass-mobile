package com.devapps.justclass

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JustClassApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@JustClassApplication)
        }
    }
}