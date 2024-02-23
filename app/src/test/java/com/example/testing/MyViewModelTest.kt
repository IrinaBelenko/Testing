package com.example.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


class MyViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun getSuccessResponse() {
        val repository = Mockito.mock(Repository::class.java)
        val successfulResponse = WeatherResponse(CurrentWeather("21"))
        val viewModel = MyViewModel(repository)
        var eventList = mutableListOf<MyViewModel.WeatherState>()
        viewModel.weatherState.observeForever {
            eventList.add(it)
        }
        runBlocking {
            Mockito.`when`(repository.getWeather()).thenReturn(successfulResponse)
        }
    }

    @Test
    fun getNullResponse() {
        val repository = Mockito.mock(Repository::class.java)
        val nullResponse = WeatherResponse(null)
        val viewModel = MyViewModel(repository)
        var eventList = mutableListOf<MyViewModel.WeatherState>()
        viewModel.weatherState.observeForever {
            eventList.add(it)
        }
        runBlocking {
            Mockito.`when`(repository.getWeather()).thenReturn(nullResponse)
        }
    }

}