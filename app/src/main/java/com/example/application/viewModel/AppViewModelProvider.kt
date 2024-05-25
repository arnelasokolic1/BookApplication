package com.example.application.viewModel


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.application.UserApplication
//import com.example.application.ui.screens.AdminUsersListViewModel
import androidx.lifecycle.createSavedStateHandle

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
                userApplication().container.bookRepository //za svaki viewModel
            )

        }

        initializer {
            AdminUsersListViewModel(
                userApplication().container.userRepository //za svaki viewModel
            )

        }
    }
}

fun CreationExtras.userApplication(): UserApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UserApplication)


