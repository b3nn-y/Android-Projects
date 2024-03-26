package com.bennysamuel.weatherify.retrofitForSearching

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRetrofitInstance {
    companion object{
        private val BASE_URL = "https://api.tomorrow.io/v4/weather/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}