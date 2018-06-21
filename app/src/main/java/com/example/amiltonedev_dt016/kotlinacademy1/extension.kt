package com.example.amiltonedev_dt016.kotlinacademy1

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observeSafe(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer<T> { t ->
        t?.let(observer)
    })
}