package com.bennysamuel.dayscaluclator.output

import android.widget.DatePicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bennysamuel.dayscaluclator.SelectDateFragment

class OutputViewModel:ViewModel() {


    private val _days = MutableLiveData<DatePicker>()
    val days: LiveData<DatePicker> = _days

    fun calculateDays(): String {
//        return ""+(2024 - days.value!!)
        var day = days.value
        var age = (2024 - day?.year!!)
        var TotalDays = age.times(365)
        var TotalMonths = age.times(12)

        val finalOut = "Age = $age\nDays = $TotalDays\nMonths = $TotalMonths"
        return finalOut

    }

    fun setDays(value: DatePicker) {
        println(value)
        _days.value = value
    }







}