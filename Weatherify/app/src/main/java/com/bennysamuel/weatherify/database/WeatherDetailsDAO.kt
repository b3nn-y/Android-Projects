package com.bennysamuel.weatherify.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherDetailsDAO {
    @Upsert
    fun insert(countryInfo:WeatherTable)

    @Query("DELETE FROM weather_table")
    fun clear()

    @Query("SELECT * FROM weather_table")
    fun getAllCountries(): List<WeatherTable>
    @Query("SELECT Loc_Name FROM weather_table where Loc_Name = :loc")
    fun isPlacePresent(loc: String): String

    @Query("DELETE FROM weather_table WHERE Loc_Name = :loc")
    fun delParticularPlace(loc: String)

    @Query("SELECT lat FROM weather_table WHERE Loc_Name = :loc")
    fun getLat(loc:String): Double

    @Query("SELECT lon FROM weather_table WHERE Loc_Name = :loc")
    fun getLon(loc:String): Double
}