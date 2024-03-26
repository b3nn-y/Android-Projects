package com.bennysamuel.formsroomdb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FormDatabaseDAO {
    @Upsert
    fun insert(userData:Form)

    @Query("SELECT Name FROM form_details_table")
    fun getName():String

    @Query("SELECT Email FROM form_details_table")
    fun getEmail():String

    @Query("SELECT Age FROM form_details_table")
    fun getAge():String

    @Query("SELECT Gender FROM form_details_table")
    fun getGender():String



    @Query("DELETE FROM form_details_table")
     fun clear()
}
