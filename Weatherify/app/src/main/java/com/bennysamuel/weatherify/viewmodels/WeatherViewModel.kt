package com.bennysamuel.weatherify.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.weatherify.R
import com.bennysamuel.weatherify.database.WeatherDetailsDAO
import com.bennysamuel.weatherify.database.WeatherTable
import com.bennysamuel.weatherify.homeRecyclerView.HomeRecyclerViewData
import com.bennysamuel.weatherify.retrofitForSearching.SearchApiInterface
import com.bennysamuel.weatherify.retrofitForSearching.SearchRetrofitInstance
import com.bennysamuel.weatherify.retrofitForSearching.WeatherData
import com.bennysamuel.weatherify.suggestionsRecyclerView.SuggestionsRecyclerData
import com.bennysamuel.weatherify.suggestionsRetrofit.Feature
import com.bennysamuel.weatherify.suggestionsRetrofit.SuggestionsApiInterface
import com.bennysamuel.weatherify.suggestionsRetrofit.SuggestionsRetroFitInstance
import com.bennysamuel.weatherify.suggestionsRetrofit.SuggestionsRetroFitInstance2
import com.bennysamuel.weatherify.weatherApi.Weather
import com.bennysamuel.weatherify.weatherApi.WeatherApiService
import com.bennysamuel.weatherify.weatherApi.WeatherRetroFitInstance
import retrofit2.Response
import retrofit2.create

class WeatherViewModel(
    val dataBase: WeatherDetailsDAO,
    application: Application
): AndroidViewModel(application) {
    lateinit var responseLiveData: LiveData<Response<WeatherData>>
    lateinit var detailedFragemnt: SuggestionsRecyclerData
    var currentSearchedWeather = ""
    lateinit var weather: Weather
    var currLocation = ""
    var dayStaus = ""

    var deviceLocation: String = ""

    suspend fun getWeatherSearchData(location: String): WeatherData? {
        val retrofitInstance = SearchRetrofitInstance.getRetrofitInstance().create(SearchApiInterface::class.java)
        println("ghey")
        println(location.split(" ").joinToString("%20"))
        var response = retrofitInstance.getWeatherData(location)
        if (response.isSuccessful){
            println(response.body()?.location)
            println(response.body())
            println(response.body()?.location!!.lat)
            println(response.body()?.location!!.lon)
            println(response.body()?.location!!.type)
            println(response.body()?.location!!.name)

        }
        else{
            response = retrofitInstance.getWeatherData2(location)
            if (response.isSuccessful){
                println(response.body()?.location)
                println(response.body())
                println(response.body()?.location!!.lat)
                println(response.body()?.location!!.lon)
                println(response.body()?.location!!.type)
                println(response.body()?.location!!.name)

            }
            else{
                response = retrofitInstance.getWeatherData3(location)
                if (response.isSuccessful){
                    println(response.body()?.location)
                    println(response.body())
                    println(response.body()?.location!!.lat)
                    println(response.body()?.location!!.lon)
                    println(response.body()?.location!!.type)
                    println(response.body()?.location!!.name)

                }

            }

        }
        return response.body()

    }


    suspend fun getSuggestions(query: String): ArrayList<SuggestionsRecyclerData> {
        var recyclerList = ArrayList<SuggestionsRecyclerData>()
        var suggestions = HashMap<Int, MutableList<String>>()
        val retrofitInstance2 = SuggestionsRetroFitInstance.getRetrofitInstance().create(SuggestionsApiInterface::class.java)
        val response2 = retrofitInstance2.getSuggestions(query)
        var count = 0
        if (response2.isSuccessful){
            println(response2.body()?.features)
            println(response2.body())
            for (i in response2.body()?.features!!){
                var temp = i.properties
                println("city "+ temp.city)
                println("category "+ temp.category)
                println("country "+ temp.country)
                println("county "+ temp.county)
                println("district "+ temp.district)
                println("name "+ temp.name)
                println("state "+ temp.state)
                println("street "+ temp.street)
                println("hamlet "+ temp.hamlet)
                println("postcode "+ temp.postcode)
                println("state District "+ temp.state_district)
                var outString = ""
                var tempList = mutableListOf<String>()
                if(temp.city!=null ){
                    tempList.add(temp.city)
                }else{
                    if (temp.county!= null){
                        tempList.add(temp.county)
                    }
                    else{
                        if (temp.state_district!= null){
                            tempList.add(temp.state_district)
                        }
                        else{
                            if (temp.district != null){
                                tempList.add(temp.district)
                            }
                            else{
                                if(temp.state != null){
                                    tempList.add(temp.state)
                                }
                                else{
                                    if(temp.hamlet != null){
                                        tempList.add(temp.hamlet)
                                    }
                                    else{
                                        if (temp.country != null){
                                            tempList.add(temp.country)
                                        }
                                        else{
                                            tempList.add("Nothing")
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                if (temp.city != null ){
                    outString += "City: " + temp.city +"\n"
                }
                if (temp.county!= null){
                    outString += "County: " + temp.county +"\n"
                }
                if (temp.state_district != null){
                    outString += "State District: " + temp.state_district +"\n"
                }
                if (temp.district != null){
                    outString += "District: " + temp.district +"\n"
                }
                if (temp.state != null){
                    outString += "State: " + temp.state +"\n"
                }
                if (temp.country != null){
                    outString += "Country: " + temp.country +"\n"
                }
                println("out: "+ outString)
                tempList.add(outString)
                tempList.add(temp.lat.toString())
                tempList.add(temp.lon.toString())

                suggestions[count++] = tempList

                println("\n\n\n\n"+ outString)

                recyclerList.add(SuggestionsRecyclerData(outString ,  tempList[0]))

                println("-----------------------------------")

            }

//            println(response.body()?.location!!.lat)
//            println(response.body()?.location!!.lon)
//            println(response.body()?.location!!.type)
//            println(response.body()?.location!!.name)

        }
        return recyclerList
    }

    suspend fun getWeather(lat: Double, long: Double): Weather? {
        println(lat)
        println(long)
        val retrofitInstance3 = WeatherRetroFitInstance.getRetrofitInstance().create(WeatherApiService::class.java)
        val response3 = retrofitInstance3.getWeather(lat, long)
        if (response3.isSuccessful){
            if (response3.body() != null){
                println(response3.body()!!.timezone)
                return response3.body()
            }
        }
        else{
            println(response3.code())

        }
        return null
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun findLocation(lat:Double, long: Double){
        val retrofitInstance3 = SuggestionsRetroFitInstance2.getRetrofitInstance().create(SuggestionsApiInterface::class.java)
        val response3 = retrofitInstance3.getLocation(lat, long)
        if (response3.isSuccessful){
            if (response3.body() != null)
                println(response3.body())
                if (response3.body()?.features?.get(0)?.properties?.city  != null){
                    deviceLocation = response3.body()?.features?.get(0)?.properties?.city!!
                }
                else{
                    if (response3.body()?.features?.get(0)?.properties?.state != null){
                        deviceLocation = response3.body()?.features?.get(0)?.properties?.state!!
                    }
                    else{
                        if (response3.body()?.features?.get(0)?.properties?.country != null){
                            deviceLocation = response3.body()?.features?.get(0)?.properties?.country!!
                        }
                    }
                }


            }

        else{
            println(response3.code())
        }
    }

    fun insert(location: String, lat:Double, long: Double){
        if(dataBase.isPlacePresent(location) == null){
            dataBase.insert(WeatherTable(location = location, lat = lat, lon = long))
        }
    }

    suspend fun searchDatabase(): ArrayList<HomeRecyclerViewData> {
        var homeRecyclerList = ArrayList<HomeRecyclerViewData>()
        var data = dataBase.getAllCountries()
        if (deviceLocation != ""){
            try {
                var placeData = getWeatherSearchData(deviceLocation)
                var weather = getWeather(placeData?.location?.lat!!, placeData.location.lon)
                homeRecyclerList.add(HomeRecyclerViewData(placeData.location.name, deviceLocation+"(Current)", weather?.current?.relative_humidity_2m.toString(), if (weather?.current?.is_day == 0){
                    R.drawable.moon}else{R.drawable.sun}, weather?.current?.temperature_2m!!))
            }
            catch (e:Exception){
                println(e.message)
            }
        }
        for (i in data){
            try{

                var weather = getWeather(dataBase.getLat(i.location), dataBase.getLon(i.location))
                homeRecyclerList.add(HomeRecyclerViewData(i.location, i.location, weather?.current?.relative_humidity_2m.toString(), if (weather?.current?.is_day == 0){
                    R.drawable.moon}else{R.drawable.sun}, weather?.current?.temperature_2m!!))
            }
            catch (e:Exception){
                println(e.message )
            }
        }

        return homeRecyclerList
    }

    fun isPlacePresent(loc:String): Boolean {
        var a: String? = dataBase.isPlacePresent(loc)
        if (a != null){
            return true
        }
        return false
    }

    fun delParticularPlace(loc: String){
        dataBase.delParticularPlace(loc)
    }
}