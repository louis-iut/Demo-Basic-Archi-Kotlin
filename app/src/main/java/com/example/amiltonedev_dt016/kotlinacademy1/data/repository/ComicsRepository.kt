package com.example.amiltonedev_dt016.kotlinacademy1.data.repository

import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.APIManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.manager.CacheManager
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.mapper.RemoteEntityDataMapper
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import io.reactivex.Observable
import io.reactivex.functions.Function

class ComicsRepository(private val apiManager: APIManager, private val cacheManager: CacheManager) {
    //region ComicsList
    fun retrieveComics(): Observable<List<Comic>> {
        return getComicsFromCache().onErrorResumeNext(Function {
            getComicsFromAPI()
        })
    }

    private fun getComicsFromAPI(): Observable<List<Comic>> {
        return apiManager.getComics()
                .map { RemoteEntityDataMapper.mapRemoteComicsToEntities(it) }
                .doOnNext { setComicsInCache(it) }
    }

    private fun getComicsFromCache(): Observable<List<Comic>> {
        val comics = cacheManager.getComics()

        if (comics.isEmpty()) {
            return Observable.error(Throwable("comics cache is empty"))
        }

        return Observable.fromArray(comics)
    }

    private fun setComicsInCache(comics: List<Comic>) {
        cacheManager.setComics(comics)
    }
    //endregion

    //region Comic
    fun retrieveComicWithId(id: String): Observable<Comic> {
        return retrieveComicWithIdFromCache(id)
    }

    private fun retrieveComicWithIdFromCache(id: String): Observable<Comic> {
        val comic = cacheManager.getComicById(id) ?: return Observable.error(Throwable("comic with id: $id doesn't exist"))

        return Observable.just(comic)
    }
    //endregion
}