package com.bennysamuel.quizonchennai.ViewModels

import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource

class GameViewModel: ViewModel() {

    var questionOne = false
    var questionOneChoice = 0
    var questionTwoChoice = 0
    var questionThreeChoice = 0
    var questionTwo = false
    var questionThree = false


    fun score(): String {
        var score = 0
        if(questionOne){
            score++
        }
        if(questionTwo){
            score++
        }
        if(questionThree){
            score++
        }

        return ""+score

    }

    fun clear(){
        questionOne = false
        questionOneChoice = 0
        questionTwoChoice = 0
        questionThreeChoice = 0
        questionTwo = false
        questionThree = false
    }

}