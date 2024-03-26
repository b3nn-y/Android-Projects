package com.bennysamuel.zinder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MatchesViewModel: ViewModel() {
    val _matches = MutableLiveData<Int>()
    val matches: LiveData<Int> = _matches

    init {
        _matches.value = 0
    }

    fun incMatch() {
        var randNum = Random.nextInt(3)
        if (randNum <= 1) {
             _matches.value = matches.value?.plus(1)
        }
    }
    fun restart(){
        _matches.value  = 0
    }


}