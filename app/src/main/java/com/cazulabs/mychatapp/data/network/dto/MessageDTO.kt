package com.cazulabs.mychatapp.data.network.dto

data class MessageDTO(val msg: String, val hour: String, val date: String, val user: UserDTO)

data class UserDTO(val username: String, val admin: Boolean = false)
