package com.bennysamuel.headsortails.ViewModels

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TossResultViewModel: ViewModel() {


    var tossValue = "head"

    fun toss(){
        var randNumber = Random.nextInt(2)
        if(randNumber==1){
            tossValue = "head"
        }
        else{
            tossValue = "tails"
        }
    }
}