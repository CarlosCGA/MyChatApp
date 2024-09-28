package com.cazulabs.mychatapp.data.network.response

import com.cazulabs.mychatapp.domain.model.MessageModel
import com.cazulabs.mychatapp.domain.model.UserModel

//Data model used to receive information from API
data class MessageResponse(
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
) {
    fun toDomain(): MessageModel {
        return MessageModel(
            msg = msg.orEmpty(),
            hour = hour ?: "no",
            date = date ?: "date",
            user = UserModel(
                username = user?.username ?: "Guess",
                admin = user?.admin ?: false
            )
        )
    }
}

data class UserResponse(val username: String? = null, val admin: Boolean? = null)
