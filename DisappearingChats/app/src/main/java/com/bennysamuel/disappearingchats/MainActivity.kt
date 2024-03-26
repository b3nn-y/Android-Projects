package com.bennysamuel.disappearingchats

import ChatViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bennysamuel.disappearingchats.ui.theme.DisappearingChatsTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisappearingChatsTheme {
                val chatViewModel = viewModel<ChatViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Chats(chatViewModel)
                    BottomSheetChatBar()
                }
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
    DisappearingChatsTheme {
        Greeting("Android")
    }
}

@Composable
fun Chats(chatViewModel: ChatViewModel) {
    val chatList by  chatViewModel.chatListArr.collectAsState()
    val chatCount by rememberSaveable{ mutableStateOf(10)}
    val increasingNumber by chatViewModel.increasingNumber.collectAsState()

    LaunchedEffect(Unit) {
        for (i in 1..10) {
            chatViewModel.addMsg()
            delay(100)
//            chatViewModel.chatCount--
        }
        delay(10000)
        for (i in 1..10) {
            chatViewModel.addMsg()
            delay(1000)
//            chatViewModel.chatCount--
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(1f)){
        ChatList(chatViewModel = chatViewModel, increasingNumber = increasingNumber)
    }






}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ChatList(chatViewModel: ChatViewModel, increasingNumber: Int) {
    val chatList = chatViewModel.chatListArr.value

    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxHeight(1f)
            .fillMaxWidth()
    ) {
        items(
            items = chatList.asReversed(),
            key = { chatMessage -> chatMessage.msgID }
        ) { chatMessage ->
            if (chatMessage.lifeCycle){
                MessageItem(message = chatMessage.msg, visible = chatMessage.visible)
            }
        }
    }
}


data class ChatMessages(
    var player: String,
    var msgID: String,
    val msg: String,
    var msgSize: Int = 1,
    val msgColor: String,
    var visible: Boolean,
    var lifeCycle:Boolean = true
)

@Composable
fun MessageItem(message: String, visible: Boolean) {
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = if (visible) androidx.compose.animation.core.tween(durationMillis = 700) else androidx.compose.animation.core.tween(durationMillis = 800),
        label = "" // Adjust duration as needed
    )
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = if (visible) androidx.compose.animation.core.tween(durationMillis = 300) else androidx.compose.animation.core.tween(durationMillis = 2000),
        label = "" // Adjust duration as needed
    )


    Box(
        modifier = Modifier
            .alpha(alpha)
            .scale(scale)
            .padding(8.dp)
    ) {
        
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetChatBar() {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }


    ModalBottomSheet(
        onDismissRequest = { isSheetOpen = false },
        sheetState = sheetState,
        containerColor = Color.White

    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.chatbackground),
                contentDescription = "bg image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            
        }
    }
}