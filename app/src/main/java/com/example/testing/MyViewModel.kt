package com.example.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel (private val repository: Repository) : ViewModel() {
    private val _weatherState =
        MutableLiveData<WeatherState>(WeatherState.NoData)
    val weatherState: LiveData<WeatherState> = _weatherState
    fun getWeather() {
        _weatherState.postValue(WeatherState.Processing)
        viewModelScope.launch {
            val response = repository.getWeather()
            if (response.current_weather != null) {

                _weatherState.postValue(WeatherState.UpdatedData(response.current_weather.temperature))
            } else {
                _weatherState.postValue(WeatherState.Error)
            }
        }
    }
    sealed class WeatherState {
        object Processing: WeatherState()
        object NoData : WeatherState()
        object Error : WeatherState()
        class UpdatedData(val temperature: String) : WeatherState()
    }
}