package android.project.weatherapp.adapters

import android.content.Context
import android.project.weatherapp.data.remote.dto.Daily
import android.project.weatherapp.databinding.DailyListItemBinding
import android.project.weatherapp.utilities.Constants
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class DailyWeatherRecyclerAdapter(
    private val context: Context,
    private var dailyList : List<Daily>
) : RecyclerView.Adapter<DailyWeatherRecyclerAdapter.DailyWeatherRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherRecyclerViewHolder {
        val dailyList: DailyListItemBinding = DailyListItemBinding.inflate(LayoutInflater
            .from(context), parent, false)

        return DailyWeatherRecyclerViewHolder(dailyList)
    }

    override fun onBindViewHolder(holder: DailyWeatherRecyclerViewHolder, position: Int) {
        val daily = dailyList[position]
        holder.bind(daily)
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    inner class DailyWeatherRecyclerViewHolder(
        private val binding: DailyListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherDetails: Daily) {
            binding.tvDailyTemp.text = weatherDetails.temp.day.toString() + (Html.fromHtml("\u2103"))
            binding.tvDailyDay.text = getDayOfWeek(weatherDetails.dt.toLong())
            binding.tvDailyDate.text =
                getMonthFromTimeStamp(weatherDetails.dt.toLong()) + "," + getDayOfMonth(weatherDetails.dt.toLong())

            Glide.with(itemView.context).load(
                Constants.WEATHER_ICON_IMAGE +
                        weatherDetails.weather[0].icon +
                        Constants.WEATHER_ICON_IMAGE_TYPE
            )
                .into(binding.ivDailyIcon)

        }
    }

    fun getDayOfWeek(timestamp: Long): String {
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000)
    }

    fun getMonthFromTimeStamp(timestamp: Long): String {
        return SimpleDateFormat("MMM", Locale.ENGLISH).format(timestamp * 1000)
    }

    fun getDayOfMonth(timestamp: Long): String {
        return SimpleDateFormat("d", Locale.ENGLISH).format(timestamp * 1000)
    }

}


