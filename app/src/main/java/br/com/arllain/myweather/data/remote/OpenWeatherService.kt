package br.com.arllain.myweather.data.remote

import br.com.arllain.myweather.data.remote.model.ForecastResult
import retrofit2.http.GET
import retrofit2.http.Query
import br.com.arllain.myweather.data.remote.model.SearchResult
import retrofit2.Call

interface OpenWeatherService {

    @GET("/data/2.5/find")
    fun findCity(
        @Query("q")
        cityName: String,
        @Query("units")
        units: String,
        @Query("lang")
        lang: String,
        @Query("appid")
        appId: String
    ): Call<SearchResult>

    @GET("/data/2.5/forecast")
    fun listCityWeather(
        @Query("id")
        cityId: Long,
        @Query("units")
        units: String,
        @Query("lang")
        lang: String,
        @Query("appid")
        appId: String
    ): Call<ForecastResult>

}