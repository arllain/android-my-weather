package br.com.arllain.myweather.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private val instance = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getOpenWeatherService(): OpenWeatherService? = instance.create(OpenWeatherService::class.java)

}
