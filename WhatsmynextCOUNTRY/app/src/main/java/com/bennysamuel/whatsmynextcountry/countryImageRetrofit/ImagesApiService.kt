package com.bennysamuel.whatsmynextcountry.countryImageRetrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApiService {

    @GET("?key=41945422-71e4 163bf4c88ab4e8fd6181e&image_type=photo&pretty=true")
    suspend fun getImages2(
        @Query("q") query: String
    ): Response<PixabayResponse>


    @GET("?key=41967060-1edc300ebdacbc244dffa62ce&image_type=photo&pretty=true")
    suspend fun getImages3(
        @Query("q") query: String
    ): Response<PixabayResponse>



    @GET("?key=41967201-0f3e9f73e516cfa0a4cceac29&image_type=photo&pretty=true")
    suspend fun getImages4(
        @Query("q") query: String
    ): Response<PixabayResponse>

}