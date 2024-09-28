package com.cazulabs.mychatapp.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.mychatapp.domain.GetMessageUseCase
import com.cazulabs.mychatapp.domain.SendMessageUseCase
import com.cazulabs.mychatapp.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
) : ViewModel() {

    init {
        getMessages()
    }

    val messageList = MutableStateFlow<List<MessageModel>>(emptyList())

    fun sendMessage(msg: String) {
        sendMessageUseCase(msg)
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                Log.d("CARLOS", "INFO -> $it")
                messageList.value = it
            }
        }
    }

}