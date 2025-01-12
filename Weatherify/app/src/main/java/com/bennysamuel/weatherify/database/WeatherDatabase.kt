package com.bennysamuel.weatherify.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherTable::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase(){
    abstract val weatherDetailsDAO: WeatherDetailsDAO
    companion object{
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase{
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    name = "user_ids").allowMainThreadQueries().fallbackToDestructiveMigration().build()

                INSTANCE = instance
            }
            return instance
        }
    }
}