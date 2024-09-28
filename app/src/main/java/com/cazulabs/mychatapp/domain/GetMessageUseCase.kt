package com.cazulabs.mychatapp.domain

import com.cazulabs.mychatapp.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val service: FirebaseChatService) {

    operator fun invoke() = service.getMessage()

}