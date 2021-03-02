package br.com.arllain.myweather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.arllain.myweather.data.local.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class DataBaseApp: RoomDatabase() {

    abstract fun getFavoriteDao() : FavoriteDao

    companion object {
        private var instance: DataBaseApp? = null

        fun getInstance(context: Context): DataBaseApp{
            if (instance == null) {
                synchronized(DataBaseApp::class.java) {
                    // Criar DB
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBaseApp::class.java,
                        "weather.db"
                    ).allowMainThreadQueries().build()
                }
            }

            return instance!!
        }
    }
}