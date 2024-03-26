package com.bennysamuel.carsretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    init {
//        getCarImages()
    }

//     fun getCarImages() {
//        CarsAPI.retrofitService.getProperties().enqueue(object : retrofit2.Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    // Handle successful response
//                    val responseBody = response.body()
//                    _response.postValue(responseBody) // Update LiveData
//                } else {
//                    // Handle failure response
//                    Log.e("API Error", "Response Code: ${response.code()} - ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                // Handle network failure or other errors
//                Log.e("API Error", "Network Failure: ${t.message}")
//            }
//        })
//        println(response.value)
//    }
}
