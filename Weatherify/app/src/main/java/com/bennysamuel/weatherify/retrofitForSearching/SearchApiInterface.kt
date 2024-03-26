package com.bennysamuel.weatherify.retrofitForSearching

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiInterface {
    @GET("realtime?units=metric&apikey=QjV3LCYhNrfjIw3flf7g8kgUu9qWmXI2")
    suspend fun getWeatherData(@Query("location") location: String): Response<WeatherData>

    @GET("realtime?units=metric&apikey=0IYqXX4T37ACtK5gqVBAKL1TPxrP0KW2")
    suspend fun getWeatherData2(@Query("location") location: String): Response<WeatherData>

    @GET("realtime?units=metric&apikey=xk9V2nIC1auZah3claPQATs16aVzA5i3")
    suspend fun getWeatherData3(@Query("location") location: String): Response<WeatherData>



}
