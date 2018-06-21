package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote.RemoteComic
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MarvelAPIManagerImpl : APIManager {
    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
    }

    private var ioInterface: IOInterface

    init {
        val gson = GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        ioInterface = retrofit.create(IOInterface::class.java)
    }

    override fun getComics(): Single<List<RemoteComic>> {
        return ioInterface.getResults().map(Function {
            return@Function it.data!!.results
        })
    }

    interface IOInterface {
        @GET("comics?apikey=3a1d27d3e" +
                "bfd7097c0b6dd5a067266cf&ts=1473236363&hash=9945a7b9b2b81" +
                "45a570e619ab7680592&format=comic&dateDescriptor=lastWeek")
        fun getResults(): Single<ResultEnvelop>
    }

    inner class ResultEnvelop {
        var data: DataEnvelop? = null
    }

    inner class DataEnvelop {
        var results: List<RemoteComic>? = null
    }
}