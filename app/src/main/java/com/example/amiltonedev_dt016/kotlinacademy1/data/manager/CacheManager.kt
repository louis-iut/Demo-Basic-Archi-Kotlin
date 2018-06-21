package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic

interface CacheManager {
    fun getComics(): List<Comic>
    fun getComicById(id: String): Comic?

    fun setComics(comics: List<Comic>)
}