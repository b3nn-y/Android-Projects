package com.bennysamuel.mememania.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bennysamuel.mememania.Memes
import com.bennysamuel.mememania.MemesData
import com.bennysamuel.mememania.retrofit.MemeRetrofitInstance
import com.bennysamuel.weatherify.weatherApi.MemeApiService

class MemeViewModel: ViewModel() {
    var memeType = ""
    var memes = ArrayList<Memes>()
    var currentMeme = Memes("", "", "", "")

    suspend fun getMemes(memeType: String){
        val retrofitInstance = MemeRetrofitInstance.getRetrofitInstance().create(MemeApiService::class.java)
        try {
            val response = when(memeType){
                "dev" -> retrofitInstance.getDevMemes()
                "tamil" -> retrofitInstance.getTamilMemes()
                "adult" -> retrofitInstance.getAdultMemes()
                "dank" -> retrofitInstance.getDankMemes()
                "wholesome" -> retrofitInstance.getWholesomeMemes()
                else -> retrofitInstance.getMemes()
            }
//            val response = retrofitInstance.getDankMemes()
            if (response.isSuccessful){
                Log.i("Retrofit", "Data Received")
            }
            else{
                Log.i("Retrofit Error", response.code().toString())
            }

            for (i in response.body()?.memes?: arrayListOf()){
                memes.add(i)
            }
        }
        catch (e:Exception){
            println(e.message)
        }
    }
}