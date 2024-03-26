package com.bennysamuel.whatsmynextcountry.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryDatabase: RoomDatabase() {
    abstract val countryDatabaseDAO: CountryDatabaseDAO

    companion object{
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase{
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    name = "user_ids").allowMainThreadQueries().fallbackToDestructiveMigration().build()

                INSTANCE = instance
            }
            return instance
        }
    }
}


