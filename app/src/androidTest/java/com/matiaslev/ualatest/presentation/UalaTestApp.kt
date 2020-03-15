package com.matiaslev.ualatest.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UalaTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UalaTestApp)
            modules(emptyList())
        }
    }
}