package com.example.application.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.models.Books
import com.example.application.model.repositories.BookRepository
import com.example.application.model.repositories.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserHomeViewModel(private val bookRepository: BookRepository): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        /* State flow -> data type - sve opodatke jendog modela strpati u ovaj state flow */
        bookRepository.getBooks()
            .map { /* mapirati rezultat svake funkcije unutar Ui state  - VARIJABLE*/
                HomeUiState(it)
                // StudentsDetails(id = it.id, name = it.name, surname = it.surname, dob = it.dob.toString(), enrolmentYear = it.enrolmentYear?.value.toString(), studentID = it.studentID.toString(), email = it.email, password = it.password)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = HomeUiState()
            )
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val bookList: List<Books> = listOf())