package com.cazulabs.mychatapp.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.mychatapp.domain.GetMessageUseCase
import com.cazulabs.mychatapp.domain.GetUsernameUseCase
import com.cazulabs.mychatapp.domain.SendMessageUseCase
import com.cazulabs.mychatapp.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
        initializeUser()
    }

    private var _uiState = MutableStateFlow<ChatViewState>(ChatViewState.LOADING)
    val uiState: StateFlow<ChatViewState> = _uiState

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList

    var username = ""

    private fun initializeUser() {
        viewModelScope.launch {
            username = getUsernameUseCase()
            if (username.isNotEmpty()) {
                _uiState.value = ChatViewState.LOADED
                getMessages()
            }
        }
    }


    fun sendMessage(msg: String) {
        sendMessageUseCase(msg, username)
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                _messageList.value = it
            }
        }
    }

}

sealed class ChatViewState {
    data object LOADING : ChatViewState()
    data object LOADED : ChatViewState()
}