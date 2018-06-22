package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.db.DBComic

interface DBManager {
    fun getComics(): List<DBComic>

    fun setComics(comics: List<DBComic>)
}