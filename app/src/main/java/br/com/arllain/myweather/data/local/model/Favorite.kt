package br.com.arllain.myweather.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_favorites")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Long = -1,
    @ColumnInfo(name = "city_name")
    var city_name: String = "",
    @ColumnInfo(name = "country_name")
    var country_name: String = ""

)