package com.cazulabs.mychatapp.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.mychatapp.domain.GetMessageUseCase
import com.cazulabs.mychatapp.domain.GetUsernameUseCase
import com.cazulabs.mychatapp.domain.SendMessageUseCase
import com.cazulabs.mychatapp.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUsernameUseCase: GetUsernameUseCase
) : ViewModel() {

    init {
        getUsername()
        getMessages()
    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    var username = ""

    fun sendMessage(msg: String) {
        sendMessageUseCase(msg, username)
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                Log.d("CARLOS", "INFO -> $it")
                _messageList.value = it
            }
        }
    }

    fun getUsername() {
        viewModelScope.launch(Dispatchers.IO) {
            username = getUsernameUseCase()
            Log.d("CARLOS", "USERNAME GOT IN VIEWMODEL -> $username")
        }
    }

}