package com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic

class ComicViewDataWrapper(val comic: Comic) {

    companion object {
        const val coverImagePlaceholder = ""
    }

    val id: String = comic.id
    val title: String = comic.title
    val date: String = comic.date
    val coverImagePath: String = comic.coverImagePath ?: coverImagePlaceholder
    val creators: String = getCreatorsDescription()
    val diamondCode: String = comic.diamondCode

    private fun getCreatorsDescription(): String {
        val creators = mapCreatorsListToDataWrapper(comic.creators)
        var creatorsDescription = ""
        creators.forEach { creatorsDescription += "${it.name}: ${it.role}\n" }

        return creatorsDescription
    }
}

fun mapComicsListToDataWrapper(comics: List<Comic>): List<ComicViewDataWrapper> {
    val comicsDataWrapper = ArrayList<ComicViewDataWrapper>()
    comics.forEach { comicsDataWrapper.add(ComicViewDataWrapper(it)) }

    return comicsDataWrapper
}