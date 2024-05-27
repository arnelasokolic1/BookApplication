package com.example.application.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.application.UserApplication
import com.example.application.ui.screens.ProfileScreen


object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            LoginRegistrationViewModel(
                userApplication().container.userRepository
            )
        }
        initializer {
            UserViewModel(
                userApplication().container.userRepository,
                this.createSavedStateHandle()
            )
        }
        initializer {
            UserHomeViewModel(
                userApplication().container.bookRepository
            )
        }
        initializer {
            AddBookViewModel(
                userApplication().container.bookRepository
            )
        }
        initializer {
            AdminUsersListViewModel(
                userApplication().container.userRepository
            )
        }

        initializer {
            ProfileScreenViewModel(
                userApplication().container.userRepository,
                this.createSavedStateHandle()
            )
        }



    }
}

fun CreationExtras.userApplication(): UserApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UserApplication)
