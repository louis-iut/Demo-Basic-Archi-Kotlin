package com.example.amiltonedev_dt016.kotlinacademy1.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.amiltonedev_dt016.kotlinacademy1.di.getCurrentActivity
import com.example.amiltonedev_dt016.kotlinacademy1.di.releaseModule
import org.koin.KoinContext
import org.koin.android.ext.android.setProperty
import org.koin.dsl.module.Module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext

abstract class BaseActivity: AppCompatActivity(), KoinComponent {

    companion object {
        const val KOIN_ACTIVITY_KEY = "activity"
    }

    abstract val koinModule: Module

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setProperty(KOIN_ACTIVITY_KEY, this)
        StandAloneContext.loadKoinModules(koinModule)
    }

    override fun onRestart() {
        super.onRestart()
        setProperty(KOIN_ACTIVITY_KEY, this)
        StandAloneContext.loadKoinModules(koinModule)
    }

    override fun onResume() {
        super.onResume()
        if (getKoinContext().getCurrentActivity() != this) {
            setProperty(KOIN_ACTIVITY_KEY, this)
            StandAloneContext.loadKoinModules(koinModule)
        }
    }


    override fun onPause() {
        super.onPause()
        getKoinContext().releaseModule(javaClass.canonicalName)
    }

    private fun getKoinContext() = (StandAloneContext.koinContext as KoinContext)
}