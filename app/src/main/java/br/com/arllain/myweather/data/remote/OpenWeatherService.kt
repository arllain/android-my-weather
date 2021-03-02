package br.com.arllain.myweather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import br.com.arllain.myweather.data.remote.model.FindResult
import retrofit2.Call

interface OpenWeatherService {

    // data/2.5/find?q=recife&units=metric&lang=PT&appid=070575d54187ef0372c9360509872652

    // base url  http://api.openweathermap.org

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
    ): Call<FindResult>
}