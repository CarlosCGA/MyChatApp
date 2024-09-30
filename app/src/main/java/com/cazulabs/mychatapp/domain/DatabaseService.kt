package com.cazulabs.mychatapp.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    suspend fun saveUsername(username: String)

    suspend fun getUsername(): Flow<String>

    suspend fun logOut()

}