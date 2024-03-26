package com.bennysamuel.canvastest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.serializer.MoshiSerializer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var supabase: SupabaseClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeSupaBase()
        setContent {
            DrawingScreen()
        }
    }

    @Composable
    fun DrawingScreen() {
        realTimeDB()


        val listOfObjects = listOf(
            "apple", "banana", "car", "dog", "elephant",
            "flower", "guitar", "house", "ice cream", "jacket",
            "keyboard", "lamp", "mountain", "notebook", "orange",
            "piano", "quilt", "rainbow", "sunflower", "tree",
            "umbrella", "vase", "watermelon", "xylophone", "yacht",
            "zebra", "airplane", "bicycle", "book", "coffee",
            "desk", "elevator", "fireplace", "globe", "hamburger",
            "island", "jellyfish", "kangaroo", "lighthouse", "mailbox",
            "necklace", "ocean", "popcorn", "quill", "rocket",
            "sunglasses", "telephone", "umbrella", "violin", "wallet",
            "xylograph", "yardstick", "zeppelin", "accordion", "binoculars",
            "cactus", "dolphin", "envelope", "fountain", "grape",
            "helicopter", "igloo", "juice", "kite", "lemon",
            "marble", "necktie", "octopus", "penguin", "quiver",
            "raccoon", "spaceship", "trampoline", "unicorn", "volcano",
            "waffle", "xylograph", "yogurt", "zeppelin", "acoustic guitar",
            "beach", "candle", "dinosaur", "earphones", "feather",
            "giraffe", "honey", "illusion", "jigsaw puzzle", "kangaroo",
            "lighthouse", "magnet", "noodle", "organizer", "puzzle",
            "quill", "rose", "sundial", "teapot", "upside-down cake",
            "volcano", "wagon", "xylograph", "yo-yo", "zeppelin"
        )

        val wordToGuess = listOfObjects.random()


        lifecycleScope.launch {

            try {
                supabase
                    .from("randomWord")
                    .update(
                        {
                            Word::word setTo wordToGuess
                        }
                    ) {
                        filter {
                            Word::id eq 1
                            //or
//                            eq("id", 1)
                        }
                    }
            } catch (e: Exception) {
                initializeSupaBase()
                println(e.message)
            }
        }


        var lines by remember { mutableStateOf(mutableStateListOf<Line>()) }
        var percentDataList by remember { mutableStateOf(mutableStateListOf<dataToStoreBasedOnPercent>()) }

        var width = LocalConfiguration.current.screenWidthDp - 30

        Surface {
            Column() {

                Column {
                    Canvas(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .width(((LocalConfiguration.current.screenWidthDp) - 20).dp)
                            .height(((LocalConfiguration.current.screenWidthDp) - 20).dp)
                            .padding(20.dp)
                            .pointerInput(true) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()

                                    val line = Line(
                                        start = change.position - dragAmount,
                                        end = change.position,
                                    )


                                    val percentData = dataToStoreBasedOnPercent(
                                        start = Offset(
                                            (change.position - dragAmount).x / width,
                                            ((change.position - dragAmount).y) / width
                                        ),
                                        end = Offset(
                                            ((change.position.x) / width),
                                            (((change.position.y) / width))
                                        )
                                    )
                                    percentDataList.add(percentData)


                                    lines.add(line)

                                    lifecycleScope.launch {

                                        try {
                                            supabase
                                                .from("drawing")
                                                .update(
                                                    {
                                                        string::data setTo convertDataToString(
                                                            dataToStore(percentDataList)
                                                        )
                                                    }
                                                ) {
                                                    filter {
                                                        string::id eq 1
                                                        //or
                                                        eq("id", 1)
                                                    }
                                                }
                                        } catch (e: Exception) {
                                            initializeSupaBase()
                                            println(e.message)
                                        }
                                    }
                                }
                            }
                    ) {

//
                        var tempa = "["
                        lines.forEach { line ->
//                            println(line)
                            tempa += "CGPoint(x:${line.start.x},y:${line.start.y}),"
                            drawLine(
                                color = line.color,
                                start = line.start,
                                end = line.end,
                                strokeWidth = line.strokeWidth.toPx(),
                                cap = StrokeCap.Round
                            )
                            println((tempa + "]"))


                        }
                        println(lines.size)


                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = wordToGuess, fontSize = 40.sp)
                }
            }


        }
        
        
        

    }
    fun convertDataToString(data: dataToStore): String {
        val gson = Gson()
        val jsonString = gson.toJson(data)
        return jsonString
    }

    fun realTimeDB() {
        lifecycleScope.launch {
            // Iterate through available channels
            try {
                supabase.realtime.subscriptions.entries.forEach { (channelId, channel) ->
                    println("Channel: $channelId, Status: ${channel.status}")
                }

                // Subscribe to changes in the "drawing" table within the "public" schema
                val channel = supabase.channel("draw3")
                val dataFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public") {
                    table = "drawing"
                }

                // Handle changes in the data flow
                dataFlow.onEach { change ->
                    when (change) {
                        is PostgresAction.Delete -> println("Deleted: ")
                        is PostgresAction.Insert -> println("Inserted:")
                        is PostgresAction.Select -> println("Selected:")
                        is PostgresAction.Update -> println("Updated:")
                    }
                }.launchIn(lifecycleScope) // Use launchIn to automatically collect in the lifecycleScope

                // Subscribe to the channel
                channel.subscribe()
            }
            catch (e:Exception){
                println(e.message)
                initializeSupaBase()
            }
        }
    }

    fun initializeSupaBase(){
        supabase = createSupabaseClient(
            supabaseUrl = "https://lsbgjjmybbbrlhzukzoh.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzYmdqam15YmJicmxoenVrem9oIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDc3OTgzODcsImV4cCI6MjAyMzM3NDM4N30.WxF_LC_A-ZYfukVNO0OdZLk4xCLKHO5xylNWfkN8tDg"
        ) {
            defaultSerializer = MoshiSerializer()
            install(Postgrest)
            install(Realtime)


        }
    }
}


data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 8.dp,

)

data class dataToStore(
    var list: List<dataToStoreBasedOnPercent>
)
data class string(
    var id:Int,
    var data:String
)

data class dataToStoreBasedOnPercent(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 8.dp
)

data class Word(
    var id: Int = 1,
    var word: String
)