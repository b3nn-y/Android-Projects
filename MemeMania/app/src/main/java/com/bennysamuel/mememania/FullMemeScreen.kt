package com.bennysamuel.mememania

import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.bennysamuel.mememania.viewmodels.MemeViewModel
import org.jetbrains.annotations.Async

@Composable
fun FullMemeScreen(meme: Memes) {

//    Text("Hello xml")


    Surface (modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF6AD4DD))){
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF8F6E3))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF6AD4DD))
                    .padding(10.dp) ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text("Meme Mania", color = Color.Black, fontSize = 30.sp)
                Image(
                    painter = painterResource(R.drawable.heartmeme),
                    contentDescription = null,
                    modifier = Modifier
                        .width(70.dp)  // Set the width to 100dp
                        .height(70.dp).padding(10.dp)

                )
            }
            AsyncImage(
                model = meme.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp).background(Color.White)
            )

            Text(meme.title, modifier = Modifier.padding(30.dp, 10.dp), fontSize = 25.sp)
        }

    }

}
