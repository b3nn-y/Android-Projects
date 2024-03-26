package com.bennysamuel.websocketstesttwo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.bennysamuel.websocketstesttwo.ui.theme.WebSocketsTestTwoTheme
import kotlinx.coroutines.launch
import java.net.URI

class MainActivity : ComponentActivity() {
    private lateinit var webSocketClient: ChatWebSocketClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            send()


            WebSocketsTestTwoTheme {
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
    override fun onDestroy() {
        super.onDestroy()
        // close websocket connection
        webSocketClient.close()
    }
    fun send(){
        val serverUri = URI("ws://10.52.0.254:8080/create")
        lifecycleScope.launch {
            webSocketClient = ChatWebSocketClient(serverUri) { message ->
                // display incoming message in ListView
                runOnUiThread {
                    run {
                        val _item = HashMap<String, Any>()
                        _item["message"] = message
                        println(_item)
                    }

                }
            }
            // connect to websocket server
            webSocketClient.connect()

            try {
                // send message to websocket server
                webSocketClient.sendMessage("Vanakam")
                println("Sent ig")
            } catch (e: Exception) {
                println("Not sent")
                e.printStackTrace()
//                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WebSocketsTestTwoTheme {
        Greeting("Android")
    }
}
