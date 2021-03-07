package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastResult (
    @SerializedName("cod")
    var cod: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("list")
    var forecastList: List<Forcast>,
    @SerializedName("city")
    var city: City
)