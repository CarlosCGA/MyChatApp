package com.cazulabs.mychatapp.data.network.response

//Data model used to receive information from API
data class MessageResponse(val msg: String, val hour: String, val date: String, val user: UserResponse)

data class UserResponse(val username: String, val admin: Boolean)
