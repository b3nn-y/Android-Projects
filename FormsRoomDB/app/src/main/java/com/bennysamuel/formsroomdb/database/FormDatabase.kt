package com.bennysamuel.formsroomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Form::class], version = 2, exportSchema = false)
abstract class FormDatabase:RoomDatabase() {

    abstract val formDatabaseDAO: FormDatabaseDAO

    companion object{
        @Volatile
        private var INSTANCE: FormDatabase? = null
        fun getInstance(context: Context):FormDatabase{
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FormDatabase::class.java,
                    name = "user_ids").allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance

            }
            return instance
        }
    }

}