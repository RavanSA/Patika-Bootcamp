package android.project.weatherapp.viewmodels

import android.project.weatherapp.data.local.DefaultLocationTracker
import android.project.weatherapp.data.remote.dto.WeatherDTO
import android.project.weatherapp.data.repository.WeatherAppRepository
import android.project.weatherapp.utilities.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class WeatherFragmentViewModel
@Inject constructor(
    private val repository: WeatherAppRepository,
    private val locationTracker: DefaultLocationTracker
) : ViewModel() {

    private val _weatherData = MutableLiveData<Resources<WeatherDTO>>()
    val weatherData: LiveData<Resources<WeatherDTO>> = _weatherData

    init {
        getWeatherData()
    }

    @ExperimentalCoroutinesApi
    private fun getWeatherData() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                repository.getWeatherData(
                    latitude = location.latitude.toString(),
                    longitude = location.longitude.toString()
                ).collect{ result ->
                    _weatherData.value = result
                }
            }
        }
    }

    fun getCurrentTime(): String? {
        val df: DateFormat = SimpleDateFormat("MMM d, yyyy")
        return df.format(Calendar.getInstance().time)
    }

}