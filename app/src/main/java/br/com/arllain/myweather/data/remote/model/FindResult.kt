package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class FindResult(
    @SerializedName("cod")
    var cod: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("list")
    var cities: List<City>,
)


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
    var wind: Wind
)

data class Country(
    @SerializedName("country")
    var name: String
)

data class Weather(
    @SerializedName("main")
    var main: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String
)

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

data class  Wind (
   @SerializedName("wind")
   var speed: Double,
)