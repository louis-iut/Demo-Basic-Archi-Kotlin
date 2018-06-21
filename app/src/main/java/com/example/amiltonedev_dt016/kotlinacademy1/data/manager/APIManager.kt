package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteComic
import io.reactivex.Observable
import io.reactivex.Single

interface APIManager {
    fun getComics(): Single<List<RemoteComic>>
}