package br.com.arllain.myweather.ui.main.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.arllain.myweather.R
import br.com.arllain.myweather.data.remote.model.Forcast
import br.com.arllain.myweather.databinding.ItemCityForecastBinding
import br.com.arllain.myweather.util.DateTimeUtils
import coil.load

class ForecastAdapter: ListAdapter<Forcast,ForecastAdapter.ViewHolder>(SearchDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forcastToBind = getItem(position)
        holder.bind(forcastToBind)
    }

    inner class ViewHolder(private val binding: ItemCityForecastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(forcast: Forcast) {
            val imageUrl = "http://openweathermap.org/img/wn/${forcast.weatherList[0].icon}@4x.png"
            binding.apply {
                tvDate.text = DateTimeUtils.getRelativeDateFormat(forcast.dt)
                tvWeatherDescription.text = forcast.weatherList[0].description
                "%.0f".format(forcast.temperature.temp).also { tvTemperature.text = it }
//                tvTempCF.text = city.tempCF
                tvCloud.text =  forcast.cloud.all.toString().plus(" %")
                tvWind.text = forcast.wind.speed.toString()
                imgWeather.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_weather_placeholder)
                }

            }
        }
    }

    class SearchDiff: DiffUtil.ItemCallback<Forcast>() {
        override fun areItemsTheSame(oldItem: Forcast, newItem: Forcast) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Forcast, newItem: Forcast) = oldItem.dt == newItem.dt    }
}


