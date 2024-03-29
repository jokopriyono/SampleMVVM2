package com.example.samplemvvm2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm2.model.CountriesService
import com.example.samplemvvm2.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                            override fun onSuccess(value: List<Country>?) {
                                countries.value = value
                                countyLoadError.value = false
                                loading.value = false
                            }

                            override fun onError(e: Throwable?) {
                                countyLoadError.value = true
                                loading.value = false
                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}