package com.example.amiltonedev_dt016.kotlinacademy1

import android.app.Application
import com.example.amiltonedev_dt016.kotlinacademy1.di.appModule
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import org.koin.Koin
import org.koin.android.ext.android.startKoin

class ComicsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(this).build())
        startKoin(this, listOf(appModule))
    }
}