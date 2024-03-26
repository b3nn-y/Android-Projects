package com.bennysamuel.drawingboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    private val client = HttpClient {
        install(WebSockets)
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lifecycleScope.launch {
            }
            DrawingScreen()
        }
    }

    @Composable
    fun DrawingScreen() {
        val lines = remember {
            mutableStateListOf<Bine>()
        }
        var linesToSend = ArrayList<Points>()



        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()

                        val line = Bine(
                            start = change.position - dragAmount,
                            end = change.position
                        )
                        linesToSend.add(Points(line.start.x, line.start.y))
                        linesToSend.add(Points(line.end.x, line.end.y))
                        println(Gson().toJson(Line(linesToSend)))

                        lines.add(line)
                    }
                }
        ) {
            var a = "["
            lines.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
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
suspend fun connectToWebSocket(playerDetails: String, connectionType: String) {
    val client = HttpClient {
        install(WebSockets)
    }

    client.webSocket(
        method = HttpMethod.Get,
        host = "your-server-host", // Replace with your actual server host
        port = 8080, // Replace with your actual server port
        path = "http://127.0.0.1"
    ) {
        send(Frame.Text(Json.encodeToString(playerDetails)))


    }

    client.close()
}


data class Line(
    var points: ArrayList<Points>
)

data class Points (
    var x: Float,
    var y: Float
)

