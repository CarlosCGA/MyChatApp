package com.cazulabs.mychatapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.mychatapp.domain.SaveUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val saveUsernameUseCase: SaveUsernameUseCase) : ViewModel() {

    fun saveUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUsernameUseCase(username)
        }
    }

}