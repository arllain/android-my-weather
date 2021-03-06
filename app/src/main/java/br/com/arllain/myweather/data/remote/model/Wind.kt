package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class  Wind (
   @SerializedName("wind")
   var speed: Double,
)