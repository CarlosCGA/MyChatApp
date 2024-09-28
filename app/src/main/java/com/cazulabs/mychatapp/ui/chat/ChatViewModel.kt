package com.cazulabs.mychatapp.ui.chat

import androidx.lifecycle.ViewModel
import com.cazulabs.mychatapp.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val sendMessageUseCase: SendMessageUseCase) :
    ViewModel() {

    fun sendMessage() {
        val msg = "Hello world example message"
        sendMessageUseCase(msg)
    }

}