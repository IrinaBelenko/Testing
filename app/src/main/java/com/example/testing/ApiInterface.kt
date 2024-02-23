package com.example.testing

import retrofit2.http.GET

interface ApiInterface {
    @GET("v1/forecast?latitude=50.4547&longitude=30.5238&current_weather=true")
    suspend fun getWeather(): WeatherResponse
}