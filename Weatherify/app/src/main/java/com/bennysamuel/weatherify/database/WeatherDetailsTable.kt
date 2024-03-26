package com.bennysamuel.weatherify.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "S_No")
    var sno: Int = 0,
    @ColumnInfo(name = "Loc_Name")
    var location:String,
    @ColumnInfo("lat")
    var lat: Double,
    @ColumnInfo("lon")
    var lon: Double
) {

}