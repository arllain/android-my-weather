package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    @SerializedName("main")
    var main: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String
):Parcelable