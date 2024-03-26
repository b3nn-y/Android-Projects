package com.bennysamuel.whatsmynextcountry.countryInfoRetrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRetroFitInstance {
    companion object{
        val BASE_URL = "https://restcountries.com/"

        fun getRetrofitInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}