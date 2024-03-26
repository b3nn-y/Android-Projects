import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DrawingApp()
        }
    }

    @Composable
    fun DrawingApp() {
        var lines =  remember {
            mutableStateListOf<Line>()
        }

        var scale by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Pair(0f, 0f)) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale *= zoom
                            offset = Pair(offset.first + pan.x, offset.second + pan.y)
                        }
                    }
                    .scale(scale)
            ) {
                translate(offset.first, offset.second) {
                    lines.forEach { line ->
                        drawLine(
                            color = line.color,
                            start = line.start,
                            end = line.end,
                            strokeWidth = line.strokeWidth
                        )
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    lines = mutableStateListOf() // Clear the canvas
                }) {
                    Text("Clear")
                }

                Button(onClick = {
                    // Save the drawn content as a Base64-encoded string
                    val encodedBitmapString = createEncodedBitmapString(lines)
                    // Simulate saving the encoded string to the database
                    println("Encoded Bitmap String: $encodedBitmapString")
                }) {
                    Text("Save Drawing")
                }
            }
        }
    }

    private fun createEncodedBitmapString(lines: List<Line>): String {
        // Create a bitmap with a white background
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        // Draw the lines on the bitmap
        lines.forEach { line ->
            canvas.drawLine(
                line.start.x,
                line.start.y,
                line.end.x,
                line.end.y,
                line.paint
            )
        }

        // Convert the bitmap to a Base64-encoded string
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        var a = Base64.encodeToString(byteArray, Base64.DEFAULT)
        println(a)
        println("Hello")
        return a
    }

    data class Line(
        val start: Offset,
        val end: Offset,
        val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
        val strokeWidth: Float = 5f
    ) {
        val paint = android.graphics.Paint().apply {
            this.color = this@Line.color.toArgb()
            this.strokeWidth = this@Line.strokeWidth
            this.strokeCap = android.graphics.Paint.Cap.ROUND
        }
    }

}
