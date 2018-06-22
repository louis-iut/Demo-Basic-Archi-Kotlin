package com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.amiltonedev_dt016.kotlinacademy1.data.repository.ComicsRepository
import com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper.ComicViewDataWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ComicDetailsViewModel(private val comicsRepository: ComicsRepository): ViewModel() {

    private val disposables = CompositeDisposable()
    val comicLiveData = MutableLiveData<ComicViewDataWrapper>()

    fun startObserveComic(id: String) {
        disposables.add(comicsRepository.retrieveComicWithId(id)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = { comicLiveData.postValue(ComicViewDataWrapper(it)) },
                        onError = { print("NO $it") }
                )
        )
    }
}