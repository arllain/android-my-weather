package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String,
    @SerializedName("sys")
    var country: Country,
    @SerializedName("weather")
    var weathers: List<Weather>,
    @SerializedName("main")
    var temperature: Temperature,
    @SerializedName("wind")
    var wind: Wind,
    var tempCF: String
)