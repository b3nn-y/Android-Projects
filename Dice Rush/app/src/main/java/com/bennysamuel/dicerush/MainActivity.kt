package com.bennysamuel.dicerush

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity


import kotlin.random.Random

class MainActivity : ComponentActivity() {

    var sum = 0
    var guess = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val rollButton:Button = findViewById(R.id.button)
        randomNumberGen()
        rollButton.setOnClickListener{
            changeDiceNumber()

        }
    }

    fun changeDiceNumber(){
        val diceImage : ImageView = findViewById(R.id.image)
        var randNumber = Random.nextInt(1,7)
        var diceName = "dice$randNumber"


        changeSumValue(randNumber)
        val resourceId = resources.getIdentifier(diceName, "drawable", packageName)
        diceImage.setImageResource(resourceId)


    }



    fun randomNumberGen(){
        val randNumText: TextView = findViewById(R.id.guessText)
        val ranNum = Random.nextInt(10,20)
        guess = ranNum
        randNumText.text = "Number to guess is: ${ranNum}"
    }

    fun changeSumValue(randNum:Int){
        val sumText:TextView = findViewById(R.id.sum)

        if((sum + randNum) < guess ){
            sum += randNum
        }
        else if ((sum + randNum) == guess){
            randomNumberGen()
            sum = 0

        }
        sumText.text = "($sum)"

    }





}


