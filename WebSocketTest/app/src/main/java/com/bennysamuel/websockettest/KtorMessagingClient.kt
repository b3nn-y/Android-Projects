package com.bennysamuel.websockettest

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow

class KtorMessagingClient(
    private val client: HttpClient
): RealtimeMessagingClient {
    private var session: WebSocketSession? = null
    override fun getGameState(): Flow<GameState> {
        return flow {
            session = client.webSocketSession {
                url("ws://0.0.0.0:8080/create")
            }
            val gameState = session!!.incoming.consumeAsFlow()
        }
    }

    override suspend fun sendAction(action: String) {
        session?.outgoing?.send(Frame.Text(action))
    }

    override suspend fun close() {
        session?.close()
        session = null
    }
}