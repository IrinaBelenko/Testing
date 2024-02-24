package com.example.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
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
        viewModel.getWeather()
        Assert.assertEquals(MyViewModel.WeatherState.NoData, eventList[0])
        Assert.assertEquals(MyViewModel.WeatherState.Processing, eventList[1])
        val weatherState = eventList[2] as MyViewModel.WeatherState.UpdatedData
        Assert.assertEquals("21", weatherState.temperature)
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

        viewModel.getWeather()
        Assert.assertEquals(MyViewModel.WeatherState.NoData, eventList[0])
        Assert.assertEquals(MyViewModel.WeatherState.Processing, eventList[1])
        Assert.assertEquals(MyViewModel.WeatherState.Error, eventList[2])
    }

}