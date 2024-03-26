package com.bennysamuel.weatherify.weatherApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast?current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,showers,snowfall,cloud_cover&daily=sunrise,sunset,uv_index_max&timezone=auto")
    suspend fun getWeather(@Query("latitude") lat:Double, @Query("longitude") long: Double): Response<Weather>
    //latitude=13.0827&longitude=80.2707&

}