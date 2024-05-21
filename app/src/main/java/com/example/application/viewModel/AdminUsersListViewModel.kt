package com.example.application.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.models.Users
import com.example.application.model.repositories.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AdminUsersListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        userRepository.getUsers()
            .map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = HomeUiState()
            )
}

data class HomeUIState(val userList: List<Users> = listOf())
