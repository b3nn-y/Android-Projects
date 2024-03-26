package com.bennysamuel.drawingboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val client = HttpClient {
        install(WebSockets)
    }

    private var webSocketSession: DefaultClientWebSocketSession? = null

    fun connectWebSocket() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                webSocketSession = client.webSocketSession(
                    method = HttpMethod.Get,
                    host = "127.0.0.1",
                    port = 8080,
                    path = "/chat"
                ) {

                }
            } catch (e: Exception) {
                println("Error while connecting: ${e.localizedMessage}")
            }
        }
    }

    fun sendMessage(message: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                webSocketSession?.send(message)
            } catch (e: Exception) {
                println("Error while sending: ${e.localizedMessage}")
            }
        }
    }

    private suspend fun DefaultClientWebSocketSession.outputMessages() {
        try {
            for (message in incoming) {
                message as? Frame.Text ?: continue
                val text = message.readText()
                withContext(Dispatchers.Main) {
                    println(text)
                }
            }
        } catch (e: Exception) {
            println("Error while receiving: ${e.localizedMessage}")
        }
    }

    fun closeWebSocket() {
        GlobalScope.launch(Dispatchers.IO) {
            client.close()
        }
    }
}