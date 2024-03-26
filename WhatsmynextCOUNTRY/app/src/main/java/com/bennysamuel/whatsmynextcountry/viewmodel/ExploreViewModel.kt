package com.bennysamuel.whatsmynextcountry.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bennysamuel.whatsmynextcountry.database.Country
import com.bennysamuel.whatsmynextcountry.database.CountryDatabaseDAO
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryRecycleViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExploreViewModel(
    val dataBase: CountryDatabaseDAO,
    application: Application):AndroidViewModel(application) {

     private var countryName = mutableListOf<String>()


    private var countryCurrency = mutableListOf<String>()


    private var countryFlagLink = mutableListOf<String>()


    private var countryLocation = mutableListOf<String>()

    private var countryRegion = mutableListOf<String>()

    private var countryCapital = mutableListOf<String>()

    val imageHashMap = HashMap<String, MutableList<String>>()

    val savedImageHashMap = HashMap<String , MutableList<String>>()

    lateinit var detailedFragementData: CountryRecycleViewData

    var isCurrentFragmentWishlist = false

    fun updateImages(key: String, images: MutableList<String>) {
        imageHashMap[key] = images
    }




    val data :Flow<HashMap<String,MutableList<String>>> = flow{
        getCountryNames()
    }


    fun getCountryNames():MutableList<String>{
        return countryName
    }

    fun getCountryCapital():MutableList<String>{
        return countryCapital
    }

    fun getCountryCurrency():MutableList<String>{
        return countryCurrency
    }

    fun getCountryRegion():MutableList<String>{
        return countryRegion
    }

    fun getCountryLocation():MutableList<String>{
        return countryLocation
    }

    fun getFlagLink():MutableList<String>{
        return countryFlagLink
    }





    fun addCountryName(name:String){
        countryName.add(name)
    }

    fun addCountryCapital(name:String){
        countryCapital.add(name)
    }

    fun addCountryLocation(name:String){
        countryLocation.add(name)
    }

    fun addCountryFlag(name:String){
        countryFlagLink.add(name)
    }
    fun addCountryRegion(name:String){
        countryRegion.add(name)
    }

    fun addCountryCurrency(name:String){
        countryCurrency.add(name)
    }

    fun isTheListFull(): Boolean{
        return countryName.size>1
    }

    fun printData(){
        println(countryCapital)
        println(countryCurrency)
        println(countryName)
        println(countryRegion)
        println(countryLocation)
        println(countryFlagLink)
    }
    fun printHashMap(){
        println("Printing hashmap")
        println(imageHashMap.size)
        for (i in imageHashMap){
            println(i.key+" "+ i.value)
        }

    }

    fun getCountryIndex(name: String): Int{
//        println(countryName.size)
        for (i in  0 until countryName.size){
//            println(countryName[i].toString())
            if(countryName[i].toString()==name){
                return i
            }
        }
        return 2
    }



    fun isTheCountrySaved(name:String):String{
        if(dataBase.getSavedInfoAboutCountry(name)==null){
            return "false"
        }
        println(dataBase.getSavedInfoAboutCountry(name))
        when(dataBase.getSavedInfoAboutCountry(name)){
            null -> return "false"
            "true" -> return "true"

        }
        return "false"
    }

    fun changeSaveState(name: String, saveState: String){
        println(name+ " " + saveState)
        dataBase.updateSavedValue(saveState,name)
        if (saveState == "false"){
            dataBase.delCountry(name)
        }
    }

    fun insertData(counName: String, saveState: String){
        var countryIndex = getCountryIndex(counName)
        if(dataBase.getSavedInfoAboutCountry(counName) == null){
            dataBase.insert(Country(countryCapital = countryCapital[countryIndex], countryCurrency = countryCurrency[countryIndex], countryFlagLink = countryFlagLink[countryIndex], countryLocation = countryLocation[countryIndex], countryRegion = countryRegion[countryIndex], countryName = counName, countryHeadImage = imageHashMap[counName]?.get(0) ?: "https://upload.wikimedia.org/wikipedia/commons/d/d1/Image_not_available.png"))
        }
    }

    fun areThereAnySavedCountries(): Boolean{
        var count = dataBase.getCount()

        if (count == 0){
            return false
        }
        else{
            return true
        }
    }

    fun getDataFromSavedCountries(){
        var a = dataBase.getAllCountries()
        for (i in a){
            savedImageHashMap[i.countryName] = mutableListOf(i.countryHeadImage)
        }

    }
    fun clearSavedHashMap(){
        savedImageHashMap.clear()
    }


}