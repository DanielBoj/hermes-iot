package org.hermes.hermesiotapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IotApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}