package com.bennysamuel.canvaslivestreaming

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
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
        var viewModel = viewModel<MainActivityViewModel>()
//        realTimeDB()
        val width = LocalConfiguration.current.screenWidthDp


        var lines by remember { mutableStateOf(mutableStateListOf<Line>()) }

        var wordToDisplay by remember {
            mutableStateOf("")
        }

        lifecycleScope.launch {
            val word = supabase.from("randomWord").select().decodeSingle<Word>()
            viewModel.correctword=word.word.toString()

            println(word)
            wordToDisplay = ""
            for (i in 0 until word.word.length) {
                if (i % 2 == 0) {
                    wordToDisplay += "_"
                } else {
                    wordToDisplay += word.word[i]
                }
            }
        }

        LaunchedEffect(Unit) {
//
//            lines = "fetchLinesFromDatabase()"

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
                            is PostgresAction.Insert -> {
                                println("Inserted:")
                                var temp = supabase.from("drawing")
                                    .select(columns = Columns.list("id", "data")) {
                                        filter {
                                            string::id eq 1

                                        }
                                    }
                                println(temp.data)
//                            val gson = Gson()
//                            var t2 = gson.fromJson(temp.data, dataToStore::class.java)
//                            println(t2)
//                            t2.list.forEach {
//                                lines.clear()
//                                lines.add(it)
//                            }

                            }

                            is PostgresAction.Select -> println("Selected:")
                            is PostgresAction.Update -> {
                                println("Updated:")
                                val word = supabase.from("randomWord").select().decodeSingle<Word>()
                                println(word)
                                wordToDisplay = ""
                                for (i in 0 until word.word.length) {
                                    if (i % 2 == 0) {
                                        wordToDisplay += "_"
                                    } else {
                                        println(word.word)
                                        wordToDisplay += word.word[i]
                                    }
                                }
                                var temp = supabase.from("drawing")
                                    .select(columns = Columns.list("data")) {
                                        filter {
                                            string::id eq 1

                                        }
                                    }
                                println(temp.data.substring(1, temp.data.length - 1))
                                val gson = Gson()
                                val outerList = gson.fromJson(temp.data, List::class.java)
                                val dataString =
                                    (outerList.firstOrNull() as? Map<*, *>)?.get("data")?.toString()
                                        ?: ""
                                val t2 = gson.fromJson(dataString, dataToStore::class.java)
                                println(t2)
                                t2.list.forEach {
                                    delay(10)
                                    lines.add(
                                        Line(
                                            start = Offset(
                                                ((it.start.x) * width / 1.5F) + 20,
                                                ((it.start.y) * width / 1.5F) + 20
                                            ),
                                            end = Offset(
                                                ((it.end.x) * width / 1.5F) + 20,
                                                ((it.end.y) * width / 1.5F) + 20
                                            )
                                        )
                                    )

                                }

                                    val word2 = supabase.from("randomWord").select().decodeSingle<Word>()
                                    viewModel.correctword=word2.word.toString()

                                    println(word)
                                    wordToDisplay = ""
                                    for (i in 0 until word.word.length) {
                                        if (i % 2 == 0) {
                                            wordToDisplay += "_"
                                        } else {
                                            wordToDisplay += word.word[i]
                                        }
                                    }


                            }
                        }
                    }
                        .launchIn(lifecycleScope) // Use launchIn to automatically collect in the lifecycleScope

                    // Subscribe to the channel
                    channel.subscribe()
                } catch (e: Exception) {
                    initializeSupaBase()
                }
            }

        }
        var isAnswerGuessed by remember{
            mutableStateOf(false)
        }


        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(

                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {

                CanvasScreen(
                    lines,
                    wordToDisplay
                )

                GuessText(isVisible = isAnswerGuessed)

                var input by remember {
                    mutableStateOf("")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = input,
                        onValueChange = { input = it },
                        label = { Text(text = "Guess Your Answer") },
                        singleLine = true
                    )

                    Button(
                        onClick = {

                            if (viewModel.correctword!=null && viewModel.correctword== input){
                                isAnswerGuessed=true
                            }
                            input = ""
                                  },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Guess")
                    }
                }


            }

        }
        lifecycleScope.launch {
            while (true){
                try {
                    if (viewModel.correctword!=null && viewModel.correctword != viewModel.correctword){
                        isAnswerGuessed=false
                    }

                }
                catch (e:Exception){

                }
                delay(5000)
            }
        }


    }

    @Composable
    fun GuessText(isVisible:Boolean) {

        if(isVisible){
            Text(
                text = "Guessed",
                color = Color.Gray,


                fontSize = 30.sp,

                


            )
        }
    }

    @SuppressLint("ResourceAsColor")
    @Composable
    fun CanvasScreen(
        lines: SnapshotStateList<Line>,
        wordToDisplay: String,


        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(
                modifier = Modifier
                    .background(Color(R.color.white_test))
                    .width(((LocalConfiguration.current.screenWidthDp) - 20).dp)
                    .height(((LocalConfiguration.current.screenWidthDp) - 20).dp)

            ) {
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


            Text(
                text = " " + wordToDisplay, fontSize = 40.sp,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp)
            )
        }

    }


//    @Preview(showSystemUi = true)
//    @Composable
//    fun PreviewDrawingScreen() {
//        DrawingScreen()
//    }


    fun initializeSupaBase() {
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

data class string(
    var id: Int,
    var data: String
)

data class dataToStore(
    var list: List<dataToStoreBasedOnPersent>
)

data class OuterStructure(val data: String)

data class dataToStoreBasedOnPersent(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 8.dp
)

data class Word(
    var id: Int = 1,
    var word: String
)