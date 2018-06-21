package com.example.amiltonedev_dt016.kotlinacademy1.di

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.*
import com.example.amiltonedev_dt016.kotlinacademy1.data.repository.ComicsRepository
import com.example.amiltonedev_dt016.kotlinacademy1.ui.activity.BaseActivity
import com.example.amiltonedev_dt016.kotlinacademy1.ui.activity.MainActivity
import com.example.amiltonedev_dt016.kotlinacademy1.ui.adapter.ComicsListAdapter
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.listener.ComicsListFragmentNavigatorListener
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.MainNavigator
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.listener.MainActivityNavigatorListener
import com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel.ComicDetailsViewModel
import com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel.ComicsListViewModel
import org.koin.KoinContext
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {
    //Managers
    bean { MarvelAPIManagerImpl() as APIManager }
    bean { CacheManagerImpl() as CacheManager }

    //Repositories
    bean { ComicsRepository(get(), get()) }

    //Activity dependencies

    factory { (getProperty(BaseActivity.KOIN_ACTIVITY_KEY) as AppCompatActivity).supportFragmentManager as FragmentManager }
    factory { getProperty(BaseActivity.KOIN_ACTIVITY_KEY) as AppCompatActivity }

    //Fragment dependencies
    viewModel { ComicsListViewModel(get()) }
    viewModel { ComicDetailsViewModel(get()) }
    factory { ComicsListAdapter(get()) }

}

val mainActivityModule = applicationContext {
    context(MainActivity::class.java.canonicalName) {
        bean { MainNavigator(get(), get()) as MainActivityNavigatorListener }
        bean { MainNavigator(get(), get()) as ComicsListFragmentNavigatorListener }
    }
}

fun KoinContext.releaseModule(scope: String) {
    releaseProperties(BaseActivity.KOIN_ACTIVITY_KEY)
    releaseContext(scope)
    beanRegistry.definitions.removeAll { it.scope.name == scope }
    beanRegistry.scopes.removeAll { it.name == scope }
}

fun KoinContext.getCurrentActivity(): AppCompatActivity = getProperty(BaseActivity.KOIN_ACTIVITY_KEY)