package com.example.application.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.repositories.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    /**
     * Holds current item ui state
     */
    var usersUiState by mutableStateOf(UsersUiState())
        private set

    init {
        viewModelScope.launch { /* kad pozivamo nase funkcije iz baze, kad se suspend ufnkcija poziva u viewModleu onda se koristi
         viewModelScope*/
            usersUiState = userRepository.getOneStream(2)
                .filterNotNull()
                .first()
                .toUserUiState(true)
        }
    }
    suspend fun updateUser() {
        userRepository.update(usersUiState.usersDetails.toUsers())
    }

    //FOR LOGOUT
    fun clearUi(){
        usersUiState = UsersUiState()
    }

    fun updateUiState(userDetails: UsersDetails) {
        usersUiState =
            UsersUiState(usersDetails = userDetails, isEntryValid = false)
    }
}