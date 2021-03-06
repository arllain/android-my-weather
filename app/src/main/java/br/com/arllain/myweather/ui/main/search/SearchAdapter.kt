package br.com.arllain.myweather.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.arllain.myweather.R
import br.com.arllain.myweather.data.remote.model.City
import br.com.arllain.myweather.databinding.ItemCityBinding
import coil.load

class SearchAdapter(
        val clickAction: (City) -> Unit
): ListAdapter<City,SearchAdapter.ViewHolder>(SearchDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cityToBind = getItem(position)
        holder.bind(cityToBind)
        holder.cardView.setOnClickListener {
            clickAction(cityToBind)
        }
    }

    inner class ViewHolder(private val binding: ItemCityBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            val imageUrl = "http://openweathermap.org/img/wn/${city.weathers[0].icon}@4x.png"
            binding.apply {
                tvCityName.text = city.name
                tvCountry.text = city.country.name
                tvWeatherDescription.text = city.weathers[0].description
                "%.0f".format(city.temperature.temp).also { tvTemperature.text = it }
                tvTempCF.text = city.tempCF
                tvCloud.text = city.cloud.all.toString().plus(" %")
                tvWind.text = city.wind.speed.toString()
                imgWeather.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_weather_placeholder)
                }

            }
        }

        val cardView = binding.cardView
    }

    class SearchDiff: DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City) = oldItem == newItem
        override fun areContentsTheSame(oldItem: City, newItem: City) = oldItem.name == newItem.name    }
}


