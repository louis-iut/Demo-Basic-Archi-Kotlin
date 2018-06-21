package com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator

import android.arch.lifecycle.LifecycleObserver
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.amiltonedev_dt016.kotlinacademy1.R
import com.example.amiltonedev_dt016.kotlinacademy1.ui.fragment.ComicDetailsFragment
import com.example.amiltonedev_dt016.kotlinacademy1.ui.fragment.ComicsListFragment
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.listener.ComicsListFragmentNavigatorListener
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.listener.MainActivityNavigatorListener

class MainNavigator(
        private val fragmentManager: FragmentManager,
        private val activity: AppCompatActivity
) : LifecycleObserver, ComicsListFragmentNavigatorListener, MainActivityNavigatorListener {

    override fun gotToComicDetails(id: String) {
        fragmentManager.beginTransaction().add(R.id.activity_main_container, ComicDetailsFragment.newInstance(id)).addToBackStack("a").commit()
    }


    override fun displayComicsListFragment() {
        fragmentManager.beginTransaction().add(R.id.activity_main_container, ComicsListFragment.newInstance()).commit()
    }

    override fun onBackAction() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }
}