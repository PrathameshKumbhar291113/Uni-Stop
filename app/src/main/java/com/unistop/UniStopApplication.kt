package com.unistop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UniStopApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}