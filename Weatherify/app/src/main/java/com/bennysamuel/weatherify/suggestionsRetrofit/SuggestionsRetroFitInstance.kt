package com.bennysamuel.weatherify.suggestionsRetrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionsRetroFitInstance {
    companion object{
        private val BASE_URL = "https://api.geoapify.com/v1/geocode/autocomplete/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}

class SuggestionsRetroFitInstance2 {
    companion object{
        private val BASE_URL = "https://api.geoapify.com/v1/geocode/reverse/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}