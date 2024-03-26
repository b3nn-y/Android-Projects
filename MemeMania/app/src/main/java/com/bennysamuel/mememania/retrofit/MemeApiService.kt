package com.bennysamuel.weatherify.weatherApi

import com.bennysamuel.mememania.MemesData
import retrofit2.Response
import retrofit2.http.GET

interface MemeApiService {
    @GET("tamilmemes/50")
    suspend fun getTamilMemes(): Response<MemesData>

    @GET("programmerhumor/50")
    suspend fun getDevMemes(): Response<MemesData>

    @GET("memes/50")
    suspend fun getMemes(): Response<MemesData>

    @GET("wholesomememes/50")
    suspend fun getWholesomeMemes(): Response<MemesData>

    @GET("adultmeme/50")
    suspend fun getAdultMemes(): Response<MemesData>

    @GET("dankmemes/50")
    suspend fun getDankMemes(): Response<MemesData>
}