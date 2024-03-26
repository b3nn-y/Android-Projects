package com.bennysamuel.websockettest

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val client: RealtimeMessagingClient
) : ViewModel() {


    val state = client
        .getGameState()
        .onStart { _isConnecting.value = true }
        .onEach { _isConnecting.value = false
        println(it)}
        .catch { t -> _showConnectionError.value = t is ConnectException
        println(""+ t +" " + _showConnectionError.value)}
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GameState())

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting = _isConnecting.asStateFlow()

    private val _showConnectionError = MutableStateFlow(false)
    val showConnectionError = _showConnectionError.asStateFlow()

    fun finishTurn(x: Int, y: Int) {
        if(state.value.field[y][x] != null || state.value.winningPlayer != null) {
            return
        }

        viewModelScope.launch {
//            client.sendAction(MakeTurn(x, y))
        }
    }

    fun send(){
        viewModelScope.launch {
            client.sendAction("Hellos from socket")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            client.close()
        }
    }
}


