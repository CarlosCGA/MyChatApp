package com.cazulabs.mychatapp.domain

interface DatabaseService {

    suspend fun saveUsername(username: String)

}