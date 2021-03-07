package br.com.arllain.myweather.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable
