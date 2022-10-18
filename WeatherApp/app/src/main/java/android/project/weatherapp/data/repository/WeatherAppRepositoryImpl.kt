package android.project.weatherapp.data.repository

import android.project.weatherapp.data.remote.WeatherAppAPI
import android.project.weatherapp.data.remote.dto.WeatherDTO
import android.project.weatherapp.utilities.Resources
import android.util.Log
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherAppRepositoryImpl @Inject constructor(
    private val api: WeatherAppAPI
) : WeatherAppRepository {


    override fun getWeatherData(
        latitude: String,
        longitude: String
    ): Flow<Resources<WeatherDTO>> = flow {
        emit(Resources.Loading<WeatherDTO>(true))
        val financialNewsList = api.getWeatherData(latitude = latitude, longitude = longitude)
        emit(Resources.Success<WeatherDTO>(data = financialNewsList))
        }.catch { error ->
        emit(Resources.Error<WeatherDTO>("Error Occurred"))
        }.flowOn(Dispatchers.IO)

}