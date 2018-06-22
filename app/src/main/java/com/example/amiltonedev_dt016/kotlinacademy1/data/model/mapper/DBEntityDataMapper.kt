package com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.db.DBComic
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.db.DBCreator
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Creator
import java.util.ArrayList

//region DB To POJO

fun mapDBComicsToEntities(dbEntities: List<DBComic>): List<Comic> {
    val comics = ArrayList<Comic>()
    dbEntities.forEach { comics.add(mapDBComicToEntity(it)) }

    return comics
}

fun mapDBCreatorsToEntities(dbEntities: List<DBCreator>): List<Creator> {
    val creators = ArrayList<Creator>()
    dbEntities.forEach { creators.add(mapDBCreatorToEntity(it)) }

    return creators
}

fun mapDBComicToEntity(dbComic: DBComic) = Comic(
        id = dbComic.id,
        title = dbComic.title,
        date = dbComic.date,
        diamondCode = dbComic.diamondCode,
        coverImagePath = dbComic.coverImagePath,
        creators = mapDBCreatorsToEntities(dbComic.creators!!)
)

fun mapDBCreatorToEntity(dbCreator: DBCreator) = Creator(
        name = dbCreator.name,
        role = dbCreator.role
)
//endregion

//region POJO to DB
fun mapComicsToDBEntities(entities: List<Comic>): List<DBComic> {
    val comics = ArrayList<DBComic>()
    entities.forEach { comics.add(mapComicToDBEntity(it)) }

    return comics
}

fun mapCreatorsToDBEntities(entities: List<Creator>, comicsId: String): List<DBCreator> {
    val creators = ArrayList<DBCreator>()
    entities.forEach { creators.add(mapCreatorToDBEntity(it, comicsId)) }

    return creators
}

fun mapComicToDBEntity(comic: Comic) = DBComic(
        id = comic.id,
        title = comic.title,
        date = comic.date,
        diamondCode = comic.diamondCode,
        coverImagePath = comic.coverImagePath,
        creators = mapCreatorsToDBEntities(comic.creators, comic.id)
)

fun mapCreatorToDBEntity(creator: Creator, comicsId: String) = DBCreator(
        name = creator.name,
        role = creator.role,
        comicsId = comicsId
)
//endregion