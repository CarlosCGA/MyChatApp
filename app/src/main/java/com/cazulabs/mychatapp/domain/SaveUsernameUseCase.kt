package com.cazulabs.mychatapp.domain

import javax.inject.Inject

class SaveUsernameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(username: String) {
        databaseService.saveUsername(username)
    }

}