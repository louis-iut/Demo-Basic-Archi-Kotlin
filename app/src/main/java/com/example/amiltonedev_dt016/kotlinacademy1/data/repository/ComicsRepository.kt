package com.example.amiltonedev_dt016.kotlinacademy1.data.repository

import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.APIManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.CacheManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper.RemoteEntityDataMapper
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import io.reactivex.Single

class ComicsRepository(private val apiManager: APIManager, private val cacheManager: CacheManager) {
    //region ComicsList
    fun retrieveComics(): Single<List<Comic>> {
        return getComicsFromCache().onErrorResumeNext {
            getComicsFromAPI()
        }
    }

    fun getComicsFromAPI(): Single<List<Comic>> {
        return apiManager.getComics()
                .map {
                    return@map RemoteEntityDataMapper.mapRemoteComicsToEntities(it)
                }.doAfterSuccess { setComicsInCache(it) }
    }

    private fun getComicsFromCache(): Single<List<Comic>> {
        val comics = cacheManager.getComics()

        if (comics.isEmpty()) {
            return Single.error(Throwable("comics cache is empty"))
        }

        return Single.just(comics)
    }

    private fun setComicsInCache(comics: List<Comic>) {
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