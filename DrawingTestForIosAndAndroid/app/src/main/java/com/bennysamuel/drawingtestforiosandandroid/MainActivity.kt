package com.bennysamuel.drawingtestforiosandandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bennysamuel.drawingtestforiosandandroid.ui.theme.DrawingTestForIosAndAndroidTheme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawingTestForIosAndAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DrawingScreen()
                }
            }
        }
    }
}



@Composable
fun DrawingScreen(){
    UserDrawingScreen()
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun UserDrawingScreen(){


    var lines by remember { mutableStateOf(mutableStateListOf<Bine>()) }
    var linesToSend = ArrayList<Points>()




    Surface {
        Column() {

            Column {
                androidx.compose.foundation.Canvas(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .width(400.dp)
                        .height(400.dp)
                        .padding(20.dp)
                        .pointerInput(true) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()

                                val line = Bine(
                                    start = change.position - dragAmount,
                                    end = change.position,
                                )
                                linesToSend.add(Points(line.start.x, line.start.y))
                                linesToSend.add(Points(line.end.x, line.end.y))

                                lines.add(line)



                            }
                        }
                ) {

                    lines.forEach { line ->
                        drawLine(
                            color = line.color ,
                            start = line.start * 4F  ,
                            end = line.end * 4F ,
                            strokeWidth = line.strokeWidth.toPx(),
                            cap = StrokeCap.Round
                        )
                        println(line)
                    }
                    println(lines.size)
                    println(lines)

                }

                Spacer(modifier = Modifier.height(30.dp))

            }
        }
    }
}
data class Bine(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 8.dp
)

data class Line(
    var points: ArrayList<Points>
)

data class Points (
    var x: Float,
    var y: Float
)

