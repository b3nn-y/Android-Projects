package com.bennysamuel.carsretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//private val baseUrl = "https://api.edmunds.com/api/media/v2/photoset?tag={t ag}&api_key={api_key}&fmt=json"

//private const val baseUrl = "https://mars.udacity.com/"
//
//private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(
//    baseUrl).build()


interface CarsApiService {
    @GET("/photos")

    suspend fun getProperties(): Response<CarPicCollection>


}

//object CarsAPI {
//    val retrofitService: CarsApiService by lazy {
//        retrofit.create(CarsApiService::class.java)
//    }
//
//}