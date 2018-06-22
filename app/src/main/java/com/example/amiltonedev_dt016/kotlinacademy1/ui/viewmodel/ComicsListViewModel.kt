package com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Comic
import com.example.amiltonedev_dt016.kotlinacademy1.data.repository.ComicsRepository
import com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper.ComicViewDataWrapper
import com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper.mapComicsListToDataWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ComicsListViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {
    private val disposables = CompositeDisposable()
    val comicsLiveData = MutableLiveData<List<ComicViewDataWrapper>>()
    val errorLiveData = SingleLiveEvent<Throwable>()
    val viewState = MutableLiveData<ComicsListViewState>()
    private val comics = ArrayList<Comic>()
    private var loadComicsFromDB = true

    private val comicsListViewState = ComicsListViewState()

    init {
        startObserveComics()
        startObserveComicsFromAPI()
    }

    private fun startObserveComics() {
        viewState.update { displayingLoading = true }
        disposables.add(comicsRepository.retrieveComics()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = {
                            if (loadComicsFromDB) {
                                viewState.update {
                                    displayingPlaceHolder = false
                                    displayingLoading = false
                                }
                                comics.clear()
                                comics.addAll(it)
                                comicsLiveData.postValue(mapComicsListToDataWrapper(it))
                            }
                        },
                        onError = {}
                )
        )
    }

    private fun areComicsListEquals(l1: List<Comic>, l2: List<Comic>): Boolean {
      /*  val tmp = ArrayList<Boolean>()
        l1.forEach {
            val c1 = it
            l2.forEach {
                tmp.add(false)
                if []
            }
            for (c2 in l2) {
                if (c1 == c2) {
                    break
                }
            }
        }

        return true*/
        return false
    }

    private fun startObserveComicsFromAPI() {
        disposables.add(comicsRepository.retrieveComicsFromAPI()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = {
                            loadComicsFromDB = false
                            if (!areComicsListEquals(comics, it)) {
                                viewState.update {
                                    displayingPlaceHolder = false
                                    displayingLoading = false
                                }
                                comics.clear()
                                comics.addAll(it)
                                comicsLiveData.postValue(mapComicsListToDataWrapper(it))
                            }
                        },
                        onError = {
                            errorLiveData.postValue(it)
                        }
                )
        )
    }

    private fun MutableLiveData<ComicsListViewState>.update(block: ComicsListViewState.() -> Unit) {
        postValue(comicsListViewState.apply(block))
    }

    override fun onCleared() {
        super.onCleared()
        //ViewModel is cleared
        //Every observation of data needs to be stopped here
        disposables.dispose()
    }
}

data class ComicsListViewState(
        var displayingPlaceHolder: Boolean = true,
        var displayingLoading: Boolean = true)