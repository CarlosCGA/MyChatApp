package com.cazulabs.mychatapp.domain

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUsernameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(): String = databaseService.getUsername().first()

}