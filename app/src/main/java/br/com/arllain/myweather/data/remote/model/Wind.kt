package br.com.arllain.myweather.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class  Wind (
   @SerializedName("speed")
   var speed: Double,
): Parcelable