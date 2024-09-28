package com.cazulabs.mychatapp.domain

import com.cazulabs.mychatapp.data.network.FirebaseChatService
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val service: FirebaseChatService) {

    operator fun invoke(msg: String) {
        service.sendMessage(msg)
    }
}