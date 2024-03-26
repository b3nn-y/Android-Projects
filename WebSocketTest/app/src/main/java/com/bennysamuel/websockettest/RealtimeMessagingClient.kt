package com.bennysamuel.websockettest

import android.app.GameState
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface RealtimeMessagingClient {
    fun getGameState(): Flow<com.bennysamuel.websockettest.GameState>
    suspend fun sendAction(action: String)
    suspend fun close()
}



data class GameState(
    val playerAtTurn: Char? = 'X',
    val field: Array<Array<Char?>> = emptyField(),
    val winningPlayer: Char? = null,
    val isBoardFull: Boolean = false,
    val connectedPlayers: List<Char> = emptyList()
) {
    companion object {
        fun emptyField(): Array<Array<Char?>> {
            return arrayOf(
                arrayOf(null, null, null),
                arrayOf(null, null, null),
                arrayOf(null, null, null),
            )
        }
    }
}