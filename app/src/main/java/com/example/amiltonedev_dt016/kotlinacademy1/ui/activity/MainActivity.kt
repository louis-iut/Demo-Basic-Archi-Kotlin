package com.example.amiltonedev_dt016.kotlinacademy1.ui.activity

import android.os.Bundle
import com.example.amiltonedev_dt016.kotlinacademy1.R
import com.example.amiltonedev_dt016.kotlinacademy1.di.mainActivityModule
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.listener.MainActivityNavigatorListener
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    override val koinModule = mainActivityModule

    private val navigatorListener: MainActivityNavigatorListener by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            navigatorListener.displayComicsListFragment()
        }
    }

    override fun onBackPressed() {
        navigatorListener.onBackAction()
    }
}
