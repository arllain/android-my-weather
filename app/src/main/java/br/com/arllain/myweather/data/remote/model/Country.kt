package br.com.arllain.myweather.data.remote.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @SerializedName("country")
    var name: String
): Parcelable