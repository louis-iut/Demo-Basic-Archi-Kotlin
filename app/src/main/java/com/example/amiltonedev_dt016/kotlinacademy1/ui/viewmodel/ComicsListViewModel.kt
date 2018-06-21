package com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.amiltonedev_dt016.kotlinacademy1.data.repository.ComicsRepository
import com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper.ComicViewDataWrapper
import com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper.mapComicsListToDataWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ComicsListViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {
    private val disposables = CompositeDisposable()
    val comicsLiveData = MutableLiveData<List<ComicViewDataWrapper>>()

    init {
        startObserveComics()
    }

    private fun startObserveComics() {
        disposables.add(comicsRepository.retrieveComics()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = { comicsLiveData.postValue(mapComicsListToDataWrapper(it)) },
                        onError = { print("NO $it") }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        //ViewModel is cleared
        //Every observation of data needs to be stopped here
        disposables.dispose()
    }

}