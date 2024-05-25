package com.example.application.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.models.Books
import com.example.application.model.models.Users
import com.example.application.model.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class AdminUsersListViewModel(private val userRepository: UserRepository) : ViewModel() {


    val homeUiState: StateFlow<HomeUIStateUsers> =
        userRepository.getUsers()
            .map { HomeUIStateUsers(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = HomeUIStateUsers()
            )
    fun deleteUser(user: Users) {
        viewModelScope.launch {
            userRepository.delete(user)
        }
    }
  /*  fun updateUser(user: Users) {
        viewModelScope.launch {
            userRepository.update(user)
        } */
    }


data class HomeUIStateUsers(val userList: List<Users> = listOf())
