package com.example.teachersjournal.weather.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.teachersjournal.weather.network.WeatherProperty
import kotlinx.coroutines.launch


enum class WeatherStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {
/*

    private val _property = MutableLiveData<WeatherProperty>()
    val property: LiveData<WeatherProperty>
        get() = _property

android:text="@{viewModelWeather.response}"


    // The external immutable LiveData for the response String
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response



    init {
        getWeatherProperties()
    }
    //android:text="@{viewModelWeather.property.imgSrcUrl}"

    private fun getWeatherProperties() {
        viewModelScope.launch {
            try {
                val listResult = WeatherApi.retrofitService.getProperties()

                _response.value =
                    "Success: ${listResult.size} Mars properties retrieved"
                if (listResult.size > 0) {
                    _property.value = listResult[0]
                }

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

*/
}