package com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Creator
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteComic
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteCreator
import java.util.*

fun mapRemoteComicsToEntities(remoteEntities: List<RemoteComic>): List<Comic> {
    val comics = ArrayList<Comic>()
    remoteEntities.forEach { comics.add(mapRemoteComicToEntity(it)) }

    return comics
}

fun mapRemoteCreatorsToEntities(remoteEntities: List<RemoteCreator>): List<Creator> {
    val creators = ArrayList<Creator>()
    remoteEntities.forEach { creators.add(mapRemoteCreatorToEntity(it)) }

    return creators
}

fun mapRemoteComicToEntity(remoteComic: RemoteComic) = Comic(
        id = remoteComic.id,
        title = remoteComic.title,
        date = remoteComic.dates[0].date,
        diamondCode = remoteComic.diamondCode,
        coverImagePath = "${remoteComic.images[0].path}.${remoteComic.images[0].extension}",
        creators = mapRemoteCreatorsToEntities(remoteComic.creators.items)
)

fun mapRemoteCreatorToEntity(remoteCreator: RemoteCreator) = Creator(
        name = remoteCreator.name,
        role = remoteCreator.role
)