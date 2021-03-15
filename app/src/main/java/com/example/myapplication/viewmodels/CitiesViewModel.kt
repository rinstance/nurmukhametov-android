package com.example.myapplication.viewmodels

import androidx.lifecycle.*
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.repository.network.responses.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CitiesViewModel : ViewModel() {

    private val _cityList= MutableLiveData<List<WeatherResponse>>()
    val cityList : LiveData<List<WeatherResponse>> get() = _cityList
    val repository = WeatherRepository()

    fun setListOfNearCities(lat: Double, lon: Double, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _cityList.postValue(repository.getNearCities(latitude = lat, longitude = lon, count = count).list)
        }
    }



}