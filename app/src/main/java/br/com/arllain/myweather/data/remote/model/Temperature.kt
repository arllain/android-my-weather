package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class  Temperature (
    @SerializedName("temp")
    var temp: Double,
    @SerializedName("feels_like")
    var feelsLike : Double,
    @SerializedName("temp_min")
    var temp_min: Double,
    @SerializedName("temp_max")
    var temp_max: Double,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("humidity")
    var humidity: Double
)
