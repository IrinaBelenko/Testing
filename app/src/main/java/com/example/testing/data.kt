package com.example.testing

data class WeatherResponse (val current_weather: CurrentWeather?)

data class CurrentWeather(val temperature:String)
