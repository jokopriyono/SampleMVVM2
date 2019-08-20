package com.example.samplemvvm2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvm2.model.County

class ListViewModel : ViewModel() {
    val countries = MutableLiveData<List<County>>()
    val countyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(
            County("CountryA"),
            County("CountryB"),
            County("CountryC"),
            County("CountryD"),
            County("CountryE"),
            County("CountryF"),
            County("CountryG"),
            County("CountryH"),
            County("CountryI"),
            County("CountryJ")
        )
        countyLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}