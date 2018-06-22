package com.example.amiltonedev_dt016.kotlinacademy1.data.repository

import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.APIManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.CacheManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.DBManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper.mapComicsToDBEntities
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper.mapDBComicsToEntities
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper.mapRemoteComicsToEntities
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import io.reactivex.Single

class ComicsRepository(private val apiManager: APIManager, private val dbManager: DBManager, private val cacheManager: CacheManager) {
    //region ComicsList
    fun retrieveComics(): Single<List<Comic>> {
        return getComicsFromCache()
                .onErrorResumeNext {
                    retrieveComicsFromDB()
                }
                .onErrorResumeNext {
                    retrieveComicsFromAPI()
                }
    }

    fun retrieveComicsFromAPI(): Single<List<Comic>> {
        return apiManager.getComics()
                .map {
                    return@map mapRemoteComicsToEntities(it)
                }.doAfterSuccess {
                    saveComicsIntoDB(it)
                    saveComicsIntoCache(it)
                }
    }

    private fun retrieveComicsFromDB(): Single<List<Comic>> {
        val comics = dbManager.getComics()

        if (comics.isEmpty()) {
            return Single.error(Throwable("comics db is empty"))
        }

        return Single.just(mapDBComicsToEntities(comics)).doAfterSuccess { saveComicsIntoCache(it) }
    }

    private fun saveComicsIntoDB(comics: List<Comic>) {
        dbManager.setComics(mapComicsToDBEntities(comics))
    }

    private fun getComicsFromCache(): Single<List<Comic>> {
        val comics = cacheManager.getComics()

        if (comics.isEmpty()) {
            return Single.error(Throwable("comics cache is empty"))
        }

        return Single.just(comics)
    }

    private fun saveComicsIntoCache(comics: List<Comic>) {
        cacheManager.setComics(comics)
    }
    //endregion

    //region Comic
    fun retrieveComicWithId(id: String): Single<Comic> {
        return retrieveComicWithIdFromCache(id)
    }

    private fun retrieveComicWithIdFromCache(id: String): Single<Comic> {
        val comic = cacheManager.getComicById(id)
                ?: return Single.error(Throwable("comic with id: $id doesn't exist"))

        return Single.just(comic)
    }
    //endregion
}