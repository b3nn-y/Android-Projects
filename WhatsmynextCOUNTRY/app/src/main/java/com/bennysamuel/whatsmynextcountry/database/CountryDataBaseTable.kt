package com.bennysamuel.whatsmynextcountry.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_country_data")
data class Country(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "S_No")
    var countryID: Int = 0,
    @ColumnInfo(name = "Country_Name")
    var countryName: String,
    @ColumnInfo(name = "Country_Capital")
    var countryCapital: String,
    @ColumnInfo(name = "Country_Currency")
    var countryCurrency: String,
    @ColumnInfo(name = "Country_Region")
    var countryRegion: String,
    @ColumnInfo(name = "Country_Flag")
    var countryFlagLink: String,
    @ColumnInfo(name = "Country_Location")
    var countryLocation: String,
    @ColumnInfo(name = "Country_Image")
    var countryHeadImage: String,
    @ColumnInfo(name = "Country_Saved")
    var saved: String = "false",

) {
}