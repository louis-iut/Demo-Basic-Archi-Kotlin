package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteComic
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteCreator
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import kotlin.collections.ArrayList

class MockAPIManagerImpl: APIManager {
    override fun getComics(): Single<List<RemoteComic>> {
        val creator1 = RemoteCreator("Jean Bon", "Drawer")
        val creator2 = RemoteCreator("Jean Bonneau", "Author")
        val creator3 = RemoteCreator("Jean D'arc", "Illustrator")

        val creators = ArrayList<RemoteCreator>()
        creators.add(creator1)
        creators.add(creator2)
        creators.add(creator3)

       /* val comic1 = RemoteComic("67933", "Captain America (2017) #703", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/9/40/5b0f16c49c83a.jpg", "jpg", creators, "APR180756")
        val comic2 = RemoteComic("69382", "Rise of the Black Panther (2018) #6", Date(), "http://i.annihil.us/u/prod/marvel/i/mg/6/20/5b0f164800e7e.jpg", "jpg", creators, "APR180794")

        val comics = ArrayList<RemoteComic>()
        comics.add(comic1)
        comics.add(comic2)*/

        return Single.just(ArrayList<RemoteComic>())
    }
}