package android.project.weatherapp.data.repository

import android.project.weatherapp.data.remote.dto.WeatherDTO
import kotlinx.coroutines.flow.Flow
import android.project.weatherapp.utilities.Resources


interface WeatherAppRepository {
    
    fun getWeatherData(latitude: String, longitude: String) : Flow<Resources<WeatherDTO>>

}