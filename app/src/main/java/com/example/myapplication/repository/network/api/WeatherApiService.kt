package com.example.myapplication.repository.network.api

import com.example.myapplication.repository.network.responses.NearCitiesResponse
import com.example.myapplication.repository.network.responses.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather/")
    suspend fun getWeatherByName(
        @Query("q") cityName: String
    ) : WeatherResponse

    @GET("weather/")
    suspend fun getWeatherByCityId(
        @Query("id") cityId: Long
    ) : WeatherResponse

    @GET("find/")
    suspend fun getNearCities(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int
    ): NearCitiesResponse
}