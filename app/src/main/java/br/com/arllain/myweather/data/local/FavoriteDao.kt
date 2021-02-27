package br.com.arllain.myweather.data.local

import androidx.room.*
import br.com.arllain.myweather.data.local.model.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM tb_favorites")
    fun getAll(): List<Favorite>

    @Query("SELECT * FROM tb_favorites WHERE id = :id")
    fun getById(id: Long): Favorite
}