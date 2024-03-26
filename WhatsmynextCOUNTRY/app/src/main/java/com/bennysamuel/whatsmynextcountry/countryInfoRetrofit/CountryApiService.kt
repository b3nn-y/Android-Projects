package com.bennysamuel.whatsmynextcountry.countryInfoRetrofit

import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {

    @GET("/v3.1/all")
    suspend fun getCountriesData(): Response<Countries>
}