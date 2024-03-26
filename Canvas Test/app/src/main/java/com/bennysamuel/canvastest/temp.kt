//import android.graphics.Bitmap
//import android.graphics.Color
//import android.os.Bundle
//import android.util.Base64
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.gestures.detectTransformGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.geometry.CornerRadius
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.graphics.drawscope.translate
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.unit.dp
//import com.bennysamuel.canvastest.supabase
//import io.github.jan.supabase.SupabaseClient
//import io.github.jan.supabase.createSupabaseClient
//import io.github.jan.supabase.postgrest.Postgrest
//import io.github.jan.supabase.realtime.Realtime
//import io.github.jan.supabase.serializer.MoshiSerializer
//import java.io.ByteArrayOutputStream
//import kotlin.math.roundToInt
//
//class com.bennysamuel.canvastest.com.bennysamuel.canvastest.MainActivity : ComponentActivity() {
//    private lateinit var supabase: SupabaseClient
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        supabase = createSupabaseClient(
//            supabaseUrl = "https://lsbgjjmybbbrlhzukzoh.supabase.co",
//            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzYmdqam15YmJicmxoenVrem9oIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDc3OTgzODcsImV4cCI6MjAyMzM3NDM4N30.WxF_LC_A-ZYfukVNO0OdZLk4xCLKHO5xylNWfkN8tDg"
//        ) {
//            defaultSerializer = MoshiSerializer()
//            install(Postgrest)
//            install(Realtime)
//
//
//        }
//        setContent {
//            DrawingApp()
//        }
//    }
//
//    @Composable
//    fun DrawingApp() {
//        var lines =  remember {
//            mutableStateListOf<com.bennysamuel.canvastest.Line>()
//        }
//
//        var scale by remember { mutableStateOf(1f) }
//        var offset by remember { mutableStateOf(Pair(0f, 0f)) }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Canvas(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxSize()
//                    .pointerInput(Unit) {
//                        detectTransformGestures { _, pan, zoom, _ ->
//                            scale *= zoom
//                            offset = Pair(offset.first + pan.x, offset.second + pan.y)
//                        }
//                    }
//                    .scale(scale)
//            ) {
//                translate(offset.first, offset.second) {
//                    lines.forEach { line ->
//                        drawLine(
//                            color = line.color,
//                            start = line.start,
//                            end = line.end,
//                            strokeWidth = line.strokeWidth
//                        )
//                    }
//                }
//            }
//
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(onClick = {
//                    lines = mutableStateListOf() // Clear the canvas
//                }) {
//                    Text("Clear")
//                }
//
//                Button(onClick = {
//                    // Save the drawn content as a Base64-encoded string
//                    val encodedBitmapString = createEncodedBitmapString(lines)
//                    // Simulate saving the encoded string to the database
//                    println("Encoded Bitmap String: $encodedBitmapString")
//                }) {
//                    Text("Save Drawing")
//                }
//            }
//        }
//    }
//
//    private fun createEncodedBitmapString(lines: List<com.bennysamuel.canvastest.Line>): String {
//        // Create a bitmap with a white background
//        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
//        val canvas = android.graphics.Canvas(bitmap)
//        canvas.drawColor(Color.WHITE)
//
//        // Draw the lines on the bitmap
//        lines.forEach { line ->
//            canvas.drawLine(
//                line.start.x,
//                line.start.y,
//                line.end.x,
//                line.end.y,
//                line.paint
//            )
//        }
//
//        // Convert the bitmap to a Base64-encoded string
//        val stream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//        val byteArray = stream.toByteArray()
//        var a = Base64.encodeToString(byteArray, Base64.DEFAULT)
//        println(a)
//        println("Hello")
//        return a
//    }
//
//    data class com.bennysamuel.canvastest.Line(
//        val start: Offset,
//        val end: Offset,
//        val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
//        val strokeWidth: Float = 5f
//    ) {
//        val paint = android.graphics.Paint().apply {
//            this.color = this@com.bennysamuel.canvastest.Line.color.toArgb()
//            this.strokeWidth = this@com.bennysamuel.canvastest.Line.strokeWidth
//            this.strokeCap = android.graphics.Paint.Cap.ROUND
//        }
//    }
//
//}



//package com.bennysamuel.canvastest
//
//import android.graphics.Bitmap
//import android.graphics.Color
//import android.os.Bundle
//import android.util.Base64
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectDragGestures
//import androidx.compose.foundation.gestures.detectTransformGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.graphics.drawscope.translate
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.lifecycleScope
//import io.github.jan.supabase.SupabaseClient
//import io.github.jan.supabase.createSupabaseClient
//import io.github.jan.supabase.postgrest.Postgrest
//import io.github.jan.supabase.postgrest.from
//import io.github.jan.supabase.realtime.Realtime
//import io.github.jan.supabase.serializer.MoshiSerializer
//import kotlinx.coroutines.launch
//
//import java.io.ByteArrayOutputStream
//import kotlin.math.roundToInt
//
//class com.bennysamuel.canvastest.MainActivity : ComponentActivity() {
//    private lateinit var supabase: SupabaseClient
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        supabase = createSupabaseClient(
//            supabaseUrl = "https://lsbgjjmybbbrlhzukzoh.supabase.co",
//            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzYmdqam15YmJicmxoenVrem9oIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDc3OTgzODcsImV4cCI6MjAyMzM3NDM4N30.WxF_LC_A-ZYfukVNO0OdZLk4xCLKHO5xylNWfkN8tDg"
//        ) {
//            defaultSerializer = MoshiSerializer()
//            install(Postgrest)
//            install(Realtime)
//
//
//        }
//        setContent {
//            DrawingApp()
//        }
//    }
//
//    @Composable
//    fun DrawingApp() {
//        var lines =  remember {
//            mutableStateListOf<com.bennysamuel.canvastest.Line>()
//        }
//
//        var scale by remember { mutableStateOf(1f) }
//        var offset by remember { mutableStateOf(Pair(0f, 0f)) }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Canvas(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxSize()
//                    .pointerInput(Unit) {
//                        detectTransformGestures { _, pan, zoom, _ ->
//                            scale *= zoom
//                            offset = Pair(offset.first + pan.x, offset.second + pan.y)
//                        }
//                    }
//                    .pointerInput(Unit) {
//                        detectDragGestures { change, pan ->
//                            change.consume()
//                            val line = com.bennysamuel.canvastest.Line(
//                                start = change.position - pan,
//                                end = change.position,
//                                color = androidx.compose.ui.graphics.Color.Black,
//                                strokeWidth = 5f
//                            )
//                            lines.add(line)
//                        }
//                    }
//                    .scale(scale)
//                    .offset { IntOffset(offset.first.roundToInt(), offset.second.roundToInt()) }
//            ) {
//                lines.forEach { line ->
//                    drawLine(
//                        color = line.color,
//                        start = line.start,
//                        end = line.end,
//                        strokeWidth = line.strokeWidth
//                    )
//                }
//            }
//            Image(
//                bitmap = decodeBase64("iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0CAYAAADL1t+KAAAAAXNSR0IArs4c6QAAAARzQklUCAgI" +
//                        "CHwIZIgAAAfwSURBVHic7dXBDcAgEMCw0v13PobggYjsCfLLmpn5AICn/bcDAIBzhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                        "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                        "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                        "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                        "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABG3/VB+Qd6O0YAAAA" +
//                        "AElFTkSuQmCC").asImageBitmap(),
//
//
//                contentDescription = null,
//                modifier = Modifier
//                    .background(androidx.compose.ui.graphics.Color.Blue)
//                    .fillMaxHeight()
//                    .fillMaxWidth()// Adjust the size as needed
//                    .padding(8.dp)
//            )
//
//
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(onClick = {
//                    lines = mutableStateListOf() // Clear the canvas
//                }) {
//                    Text("Clear")
//                }
//
//                Button(onClick = {
//                    // Save the drawn content as a Base64-encoded string
//                    val encodedBitmapString = createEncodedBitmapString(lines)
//                    // Simulate saving the encoded string to the database
//                    println("Encoded Bitmap String: $encodedBitmapString")
//
//                    println("Bitmap")
//                    println(decodeBase64("iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0CAYAAADL1t+KAAAAAXNSR0IArs4c6QAAAARzQklUCAgI" +
//                            "CHwIZIgAAAfwSURBVHic7dXBDcAgEMCw0v13PobggYjsCfLLmpn5AICn/bcDAIBzhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgAB" +
//                            "hg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGG" +
//                            "DgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYO" +
//                            "AAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4A" +
//                            "AYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABhg4AAYYOAAGGDgABG3/VB+Qd6O0YAAAA" +
//                            "AElFTkSuQmCC").width)
//                }) {
//                    Text("Save Drawing")
//                }
//            }
//        }
//    }
//
//    private fun createEncodedBitmapString(lines: List<com.bennysamuel.canvastest.Line>): String {
//        // Create a bitmap with a white background
//        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
//        val canvas = android.graphics.Canvas(bitmap)
//        canvas.drawColor(Color.WHITE)
//
//        // Draw the lines on the bitmap
//        lines.forEach { line ->
//            canvas.drawLine(
//                line.start.x,
//                line.start.y,
//                line.end.x,
//                line.end.y,
//                line.paint
//            )
//        }
//
//        // Convert the bitmap to a Base64-encoded string
//        val stream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//        val byteArray = stream.toByteArray()
//        var a = Base64.encodeToString(byteArray, Base64.DEFAULT)
//        println("Below")
//        println(a)
//        val city = Data(a)
//        lifecycleScope.launch {
//            supabase.from("imageTest").insert(city)
//        }
//        println("Above")
//        println("Hello")
//        return a
//    }
//
//    data class com.bennysamuel.canvastest.Line(
//        val start: Offset,
//        val end: Offset,
//        val color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black,
//        val strokeWidth: Float = 5f
//    ) {
//        val paint = android.graphics.Paint().apply {
//            this.color = this@com.bennysamuel.canvastest.Line.color.toArgb()
//            this.strokeWidth = this@com.bennysamuel.canvastest.Line.strokeWidth
//            this.strokeCap = android.graphics.Paint.Cap.ROUND
//        }
//    }
//    private fun decodeBase64(encodedString: String): android.graphics.Bitmap {
//        val decodedByteArray = android.util.Base64.decode(encodedString, android.util.Base64.DEFAULT)
//        return android.graphics.BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
//    }
//
//
//}
//
//data class Data(val data: String)

