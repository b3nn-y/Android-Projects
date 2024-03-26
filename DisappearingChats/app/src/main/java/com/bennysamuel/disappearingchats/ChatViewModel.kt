import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bennysamuel.disappearingchats.ChatMessages
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    var _chatList = MutableStateFlow(arrayListOf("Hello", "What's up", "Goood", "Now let the party begin"))
    val chatList: StateFlow<ArrayList<String>> get() = _chatList

    private val _increasingNumber = MutableStateFlow(0)
    val increasingNumber: StateFlow<Int> = _increasingNumber


    var _chatListArr = MutableStateFlow(ArrayList<ChatMessages>())
    val chatListArr: StateFlow<ArrayList<ChatMessages>> get() = _chatListArr

    val _chatMessages2 = MutableStateFlow(HashMap<String, String>())
    val chatMessages2: StateFlow<HashMap<String, String>> get() = _chatMessages2
    val addMessages = HashMap<String, Int>()
    var chatNo = 0

    fun addMsg(){
        var chat = ChatMessages("ben", "ben${chatNo++}", "Hello$chatNo", 30, "", visible = false)
        if (!(addMessages.containsKey(chat.msgID))){
            addMessages[chat.msgID] = _chatListArr.value.size
            chatModerator(chat)
        }

    }
    fun chatModerator(chatMessages: ChatMessages): Job {
        return viewModelScope.launch {
            val updatedList = ArrayList(_chatListArr.value)
            updatedList.add(chatMessages)
            _chatListArr.value = updatedList
            _increasingNumber.value = _increasingNumber.value.plus(1)
            delay(20)
            val updatedList2 = ArrayList(_chatListArr.value)
            updatedList2[addMessages[chatMessages.msgID]!!].visible = true
            _chatListArr.value = updatedList2
//            println(updatedList2)
            _increasingNumber.value = _increasingNumber.value.plus(1)
            var chatTimer = 5
            while (chatTimer >= 0) {
                delay(1000)
                chatTimer--
            }

            val updatedList3 = ArrayList(_chatListArr.value)
            updatedList3[addMessages[chatMessages.msgID]!!].visible = false
            _chatListArr.value = updatedList3
            _increasingNumber.value = _increasingNumber.value.plus(1)
            delay(2000)
            val updatedList4 = ArrayList(_chatListArr.value)
            updatedList4[addMessages[chatMessages.msgID]!!].lifeCycle = false
            _chatListArr.value = updatedList4
            _increasingNumber.value = _increasingNumber.value.plus(1)
        }
    }
}

