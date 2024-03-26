package com.bennysamuel.carsretrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        val baseUrl = "https://jsonplaceholder.typicode.com/"

        fun getRestrofitInstance(): Retrofit{

            return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()


        }
    }
}