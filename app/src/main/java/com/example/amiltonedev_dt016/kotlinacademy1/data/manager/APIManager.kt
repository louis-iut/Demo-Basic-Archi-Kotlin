package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteComic
import io.reactivex.Observable

interface APIManager {
    fun getComics(): Observable<List<RemoteComic>>
}