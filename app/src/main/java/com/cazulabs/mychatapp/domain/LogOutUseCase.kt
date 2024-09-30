package com.cazulabs.mychatapp.domain

import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke() {
        databaseService.logOut()
    }

}