package br.com.arllain.myweather.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forcast(
    @SerializedName("dt")
    var name: Long,
    @SerializedName("main")
    var temperature: Temperature,
    @SerializedName("weather")
    var weatherList: List<Weather>,
    @SerializedName("clouds")
    var cloud:Cloud,
    @SerializedName("wind")
    var wind: Wind,
    @SerializedName("dt_txt")
    var data_txt: String
): Parcelable