package br.com.arllain.myweather.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
    @SerializedName("clouds")
    var cloud: Cloud,
    @SerializedName("timezone")
    var timezone: Long,
    var tempCF: String
): Parcelable