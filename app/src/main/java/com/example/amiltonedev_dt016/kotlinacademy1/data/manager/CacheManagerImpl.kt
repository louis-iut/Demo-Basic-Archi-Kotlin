package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import kotlin.collections.HashMap

class CacheManagerImpl: CacheManager {
    private var comics: Map<String, Comic> = HashMap()

    override fun getComics(): List<Comic> {
        return comics.values.sortedWith(compareBy(Comic::date))
    }

    override fun getComicById(id: String): Comic? {
       return comics[id]
    }

    override fun setComics(comics: List<Comic>) {
        var comicsMap = HashMap<String, Comic>()
        comics.forEach { comicsMap[it.id] = it }
        this.comics = comicsMap
    }
}