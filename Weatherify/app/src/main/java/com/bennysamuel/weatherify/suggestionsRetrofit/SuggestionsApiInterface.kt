package com.bennysamuel.weatherify.suggestionsRetrofit

//import com.bennysamuel.weatherify.suggestionsRetrofit.SearchedLocation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SuggestionsApiInterface {
    @GET("?apiKey=c04e984dc936402f850726df139264ca")
    suspend fun getSuggestions(@Query("text") text:String): Response<FeatureCollection>

    @GET("?apiKey=c04e984dc936402f850726df139264ca")
    suspend fun getLocation(@Query("lat") lat:Double, @Query("lon") long: Double): Response<SearchedLocation>
}