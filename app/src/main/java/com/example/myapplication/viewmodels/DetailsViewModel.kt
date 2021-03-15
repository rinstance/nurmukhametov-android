package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.RepositoryProvider
import com.example.myapplication.repository.network.responses.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _cityWeather = MutableLiveData<WeatherResponse>()
    val cityWeather : LiveData<WeatherResponse> get() = _cityWeather
    val repository = RepositoryProvider.weatherRepository

    fun getCityById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
           _cityWeather.postValue(repository.getCityWeatherById(id))
        }
    }
}