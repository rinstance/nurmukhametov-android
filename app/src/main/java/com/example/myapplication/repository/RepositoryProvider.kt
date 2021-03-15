package com.example.myapplication.repository

import com.example.myapplication.repository.WeatherRepository

object RepositoryProvider {
    val weatherRepository by lazy { WeatherRepository() }
}