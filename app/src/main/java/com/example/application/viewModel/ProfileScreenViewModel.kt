package com.example.application.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.repositories.UserRepository
import com.example.application.ui.screens.ProfileDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    var usersUiState by mutableStateOf(UsersUiState())
        private set

    init {
        viewModelScope.launch {
            usersUiState = userRepository.getOneStream(userId)
                .filterNotNull()
                .first()
                .toUserUiState(true)
        }
    }

    suspend fun updateUser() {
        userRepository.update(usersUiState.usersDetails.toUsers())
    }

    fun clearUi() {
        usersUiState = UsersUiState()
    }

    fun updateUiState(userDetails: UsersDetails) {
        usersUiState = UsersUiState(usersDetails = userDetails, isEntryValid = false)
    }
}
