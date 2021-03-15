package com.example.myapplication.repository


import com.example.myapplication.repository.network.api.ApiFactory
import com.example.myapplication.repository.network.api.WeatherApiService

class WeatherRepository {
    var client: WeatherApiService = ApiFactory.weatherApi

    suspend fun getNearCities(longitude: Double, latitude: Double, count: Int) =
        client.getNearCities(longitude = longitude, latitude = latitude,count = count)
    suspend fun getCityWeatherById(id: Long) = client.getWeatherByCityId(id)
    suspend fun getCityWeatherByName(cityName: String) = client.getWeatherByName(cityName)
}