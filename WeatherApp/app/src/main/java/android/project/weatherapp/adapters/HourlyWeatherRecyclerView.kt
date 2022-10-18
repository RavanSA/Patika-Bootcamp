package android.project.weatherapp.adapters

import android.content.Context
import android.project.weatherapp.data.remote.dto.Hourly
import android.project.weatherapp.databinding.HourlyItemListBinding
import android.project.weatherapp.utilities.Constants
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class HourlyWeatherRecyclerAdapter(
    private val context: Context,
    private var hourlyList : List<Hourly>
) : RecyclerView.Adapter<HourlyWeatherRecyclerAdapter.HourlyWeatherRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherRecyclerViewHolder {
        val hourlyList: HourlyItemListBinding = HourlyItemListBinding.inflate(LayoutInflater
            .from(context), parent, false)

        return HourlyWeatherRecyclerViewHolder(hourlyList)
    }

    override fun onBindViewHolder(holder: HourlyWeatherRecyclerViewHolder, position: Int) {
        val hourlyList = hourlyList[position]
        holder.bind(hourlyList)
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }

    inner class HourlyWeatherRecyclerViewHolder(
        private val binding: HourlyItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherDetails: Hourly) {

            binding.tvHourlyTemp.text = weatherDetails.temp.toString() + (Html.fromHtml("\u2103"))

            binding.tvHourlyhour.text = getHourFromTimeStamp(weatherDetails.dt.toLong())

            Glide.with(itemView.context).load(
                Constants.WEATHER_ICON_IMAGE +
                        weatherDetails.weather[0].icon +
                        Constants.WEATHER_ICON_IMAGE_TYPE
            )
                .into(binding.ivHourlyIcon)
        }
    }

    fun getHourFromTimeStamp(timestamp: Long): String {
        return SimpleDateFormat("h:mm", Locale.ENGLISH).format(timestamp * 1000)
    }

}