package android.project.weatherapp.data.remote

import android.project.weatherapp.data.remote.dto.WeatherDTO
import android.project.weatherapp.utilities.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAppAPI {

    @GET("onecall")
    suspend fun getWeatherData(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String = "minutely",
        @Query("units") units: String = "metric",
    ): WeatherDTO
    
}