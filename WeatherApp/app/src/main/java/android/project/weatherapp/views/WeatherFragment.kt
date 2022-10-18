package android.project.weatherapp.views

import android.Manifest
import android.os.Bundle
import android.project.weatherapp.adapters.DailyWeatherRecyclerAdapter
import android.project.weatherapp.adapters.HourlyWeatherRecyclerAdapter
import android.project.weatherapp.data.remote.dto.Current
import android.project.weatherapp.data.remote.dto.Daily
import android.project.weatherapp.data.remote.dto.Hourly
import android.project.weatherapp.databinding.FragmentScrollingBinding
import android.project.weatherapp.utilities.Constants
import android.project.weatherapp.utilities.Resources
import android.project.weatherapp.viewmodels.WeatherFragmentViewModel
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel by viewModels<WeatherFragmentViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var _binding: FragmentScrollingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            subscribeUi()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
    }

    @ExperimentalCoroutinesApi
    private fun subscribeUi() {

        viewModel.weatherData.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resources.Success -> {
                    result.data?.let {
                        val currentWeather = it.current
                        val hourlyWeather = it.hourly
                        val dailyWeather = it.daily

                        setUpViews(
                            currentWeather = currentWeather,
                            hourlyWeather = hourlyWeather,
                            dailyWeather = dailyWeather
                        )

                        hideProgressBar()
                    }
                }
                is Resources.Error -> {
                    Toast.makeText(requireContext(), "Error Occurred",Toast.LENGTH_LONG).show()
                }
                is Resources.Loading -> { showProgressBar() }
            }
        }
    }

    private fun setUpViews(
        currentWeather: Current,
        hourlyWeather: List<Hourly>,
        dailyWeather: List<Daily>
    ) {

        binding.rvHourlyList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        val adapterHourly =
            HourlyWeatherRecyclerAdapter(
                requireContext(),
                hourlyWeather
            )


        val adapterDaily =
            DailyWeatherRecyclerAdapter(
                requireContext(),
                dailyWeather
            )


        binding.apply {
            binding.rvHourlyList.adapter = adapterHourly
            binding.rvRecyclerViewDaily.adapter = adapterDaily

            binding.tvTemperature.text = currentWeather.temp.toString() +(Html.fromHtml("\u2103"))
            binding.tvPressure.text = currentWeather.pressure.toString() + "hpa"
            binding.tvHumidity.text = currentWeather.humidity.toString() + "%"
            binding.tvWindSpeed.text = currentWeather.wind_speed.toString() + "km/h"
            binding.tvWeatherDescription.text =
                currentWeather.weather[0].description
            binding.tvCurrentTime.text = viewModel.getCurrentTime()
        }


        Glide.with(this@WeatherFragment)
            .load(Constants.WEATHER_ICON_IMAGE + currentWeather.weather[0].icon + Constants.WEATHER_ICON_IMAGE_TYPE)
            .into(binding.ivWeatherIcon)
    }


    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}