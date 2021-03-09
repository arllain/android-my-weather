package br.com.arllain.myweather.ui.main.forecast

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arllain.myweather.R
import br.com.arllain.myweather.data.local.DataBaseApp
import br.com.arllain.myweather.data.local.model.Favorite
import br.com.arllain.myweather.data.remote.RetrofitManager
import br.com.arllain.myweather.data.remote.model.City
import br.com.arllain.myweather.data.remote.model.ForecastResult
import br.com.arllain.myweather.databinding.ActivityCityForecastBinding
import br.com.arllain.myweather.extension.isInternetAvailable
import br.com.arllain.myweather.extension.toPx
import br.com.arllain.myweather.ui.main.search.SearchFragment
import br.com.arllain.myweather.util.MarginItemDecoration
import br.com.arllain.myweather.util.ReadFile
import coil.load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CityForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityForecastBinding
    private var city: City? = null


    private val forecastAdapter by lazy {
        ForecastAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        city =  intent.getParcelableExtra<City>(SearchFragment.SEARCH_FRAGMENT_CITY_ID)
        initUi()
    }

    private fun initUi() {
        supportActionBar?.title = "Forecast"
        if (city != null) {
           listForcast(city)

            binding.rvForecast.apply {
                layoutManager = LinearLayoutManager(this@CityForecastActivity)
                adapter = forecastAdapter
                addItemDecoration(MarginItemDecoration(16.toPx()))
            }
            binding.btnFavorite.setOnClickListener {
                saveCity(city)
            }
        }
    }

    private fun saveCity(city: City?) {
        val dao = DataBaseApp.getInstance(this).getFavoriteDao()

        val favoriteFound = city?.id?.let { dao.getById(it) }

        if (favoriteFound != null){
            dao.delete(favoriteFound)
        }

        val favoriteTobeSaved = city?.id?.let {
            Favorite(it, city.name, city.country.name)
        }

        if (favoriteTobeSaved != null) {
            dao.insert(favoriteTobeSaved)
        }
    }

    private fun listForcast(city: City?) {
        if (city != null) {
            val weatherIn = getString(R.string.weatherIn)
            "$weatherIn ${city.name}, ${city.country.name}".also {
                binding.tvCityWeather.text = it
            }

            "%.0f".format(city.temperature.temp).also { binding.tvTemperature.text = it }
            binding.tvTempCF.text = city.tempCF

            binding.imgWeather.load("http://openweathermap.org/img/wn/${city.weathers[0].icon}@4x.png") {
                crossfade(true)
                placeholder(R.drawable.ic_weather_placeholder)
            }

            val file = File(this.filesDir, "owApiKey")
            val apiKey = ReadFile.readSafeFile(file, this)
            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
            val units = sharedPreference.getString(
                    "key_temperature_unit",
                    getString(R.string.unit_metric)
            ).toString()

            val lang = sharedPreference.getString("key_language", getString(R.string.lang_english)).toString()


            if (this.isInternetAvailable()) {
                val call = RetrofitManager.getOpenWeatherService()?.listCityWeather(
                    city.id,
                    units,
                    lang,
                    apiKey
                )

                call?.enqueue(object : Callback<ForecastResult> {
                    override fun onResponse(
                        call: Call<ForecastResult>,
                        response: Response<ForecastResult>
                    ) {
                        if (response.isSuccessful) {
                            forecastAdapter.submitList(response.body()?.forecastList)
                        } else {
                            Log.w(CityForecastActivity.TAG, "onResponse: ${response.message()}",)
                        }
                    }

                    override fun onFailure(call: Call<ForecastResult>, t: Throwable) {
                        Log.e(CityForecastActivity.TAG, "onFailure", t)
                    }

                })
            } else {
                Toast.makeText(this, "No network access", Toast.LENGTH_SHORT).show()
            }
        }

    }
    companion object {
        private const val TAG = "CityForecastActivity"
    }

}