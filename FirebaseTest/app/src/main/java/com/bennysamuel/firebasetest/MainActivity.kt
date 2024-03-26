package com.bennysamuel.firebasetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bennysamuel.firebasetest.ui.theme.FirebaseTestTheme

import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Greeting("Android")


                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )



        Button(
            onClick = {
                val data = hashMapOf("name" to R.drawable.ic_launcher_background)
                val fireStore = FirebaseFirestore.getInstance()
                try {
                    val document =fireStore.collection("Users").document("hello master")
                    document.set(data).addOnCompleteListener {
                        if (it.isComplete) println("success") else println("failure")
                    }
                }catch (e:Exception){
                    println(e)
                }


            },

            ) {
            Text(text = "Click me")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseTestTheme {
        Greeting("Android")
    }
}