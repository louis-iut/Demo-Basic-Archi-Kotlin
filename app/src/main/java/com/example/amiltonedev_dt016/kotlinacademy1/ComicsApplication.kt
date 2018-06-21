package com.example.amiltonedev_dt016.kotlinacademy1

import android.app.Application
import com.example.amiltonedev_dt016.kotlinacademy1.di.appModule
import org.koin.Koin
import org.koin.android.ext.android.startKoin

class ComicsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}