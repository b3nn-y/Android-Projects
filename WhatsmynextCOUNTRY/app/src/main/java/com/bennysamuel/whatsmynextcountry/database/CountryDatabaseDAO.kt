package com.bennysamuel.whatsmynextcountry.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CountryDatabaseDAO {
    @Upsert
    fun insert(countryInfo:Country)

    @Query("DELETE FROM saved_country_data")
    fun clear()

    @Query("SELECT Country_Saved FROM saved_country_data WHERE Country_Name = :country")
    fun getSavedInfoAboutCountry(country: String):String?

    @Query("UPDATE saved_country_data SET Country_Saved = :saveVal WHERE Country_Name = :country")
    fun updateSavedValue(saveVal:String,country: String)

    @Query("SELECT Country_Name FROM saved_country_data WHERE Country_Name = :name")
    fun getCounName(name: String):String

    @Query("SELECT Country_Capital FROM saved_country_data WHERE Country_Name = :name")
    fun getCounCapital(name: String):String

    @Query("SELECT Country_Currency FROM saved_country_data WHERE Country_Name = :name")
    fun getCounCurr(name: String):String

    @Query("SELECT Country_Region FROM saved_country_data WHERE Country_Name = :name")
    fun getCounRegion(name: String):String

    @Query("SELECT Country_Location FROM saved_country_data WHERE Country_Name = :name")
    fun getCounLoc(name: String):String

    @Query("SELECT Country_Flag FROM saved_country_data WHERE Country_Name = :name")
    fun getCounFlag(name: String):String

    @Query("SELECT Country_Image FROM saved_country_data WHERE Country_Name = :name")
    fun getCounImage(name: String):String

    @Query("SELECT Country_Saved FROM saved_country_data WHERE Country_Name = :name")
    fun getCounSaveState(name: String):String

    @Query("SELECT COUNT(*) FROM saved_country_data")
    fun getCount(): Int

    @Query("DELETE FROM SAVED_COUNTRY_DATA WHERE Country_Name = :name")
    fun delCountry(name:String)

    @Query("SELECT * FROM saved_country_data")
    fun getAllCountries(): List<Country>


}