package com.cazulabs.mychatapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.mychatapp.domain.GetUsernameUseCase
import com.cazulabs.mychatapp.domain.SaveUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUsernameUseCase: SaveUsernameUseCase,
    private val getUsernameUseCase: GetUsernameUseCase
) : ViewModel() {

    init {
        verifyUserLogged()
    }

    private var _uiState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val uiState: StateFlow<MainViewState> = _uiState

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val username = getUsernameUseCase()
            if (username.isNotEmpty())
                _uiState.value = MainViewState.REGISTERED
            else
                _uiState.value = MainViewState.UNREGISTERED
        }
    }

    fun saveUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUsernameUseCase(username)
        }
    }

}

sealed class MainViewState {
    object LOADING : MainViewState()
    object UNREGISTERED : MainViewState()
    object REGISTERED : MainViewState()
}