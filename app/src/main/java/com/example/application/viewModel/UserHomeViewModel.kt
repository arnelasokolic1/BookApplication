package com.example.application.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.models.Books
import com.example.application.model.repositories.BookRepository
import com.example.application.model.repositories.UserRepository
import com.example.application.ui.screens.ProfileDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserHomeViewModel(private val bookRepository: BookRepository, savedStateHandle: SavedStateHandle,private val userRepository: UserRepository,) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    private val _homeUiState = MutableStateFlow(HomeUiState(emptyList()))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()


    var usersUiState by mutableStateOf(UsersUiState())
        private set


    init {// da se kreira svaki put kad se pozove taj viewModel
        //funkcija za kreiranje box
        viewModelScope.launch {
            bookRepository.getBooks().collect { books ->
                _homeUiState.value = HomeUiState(books)
            } }}
        init { //isto se poziva svaki put kad s epoziva taj viewmodel, ali sad ce pozvait i usera preko ID
                viewModelScope.launch {
             usersUiState = userRepository.getOneStream(userId)
                .filterNotNull()
                .first()
                .toUserUiState(true)

            Log.d("UserDashboard",usersUiState.toString())

        }
    }

    fun deleteBook(book: Books) {
        viewModelScope.launch {
            bookRepository.delete(book)
        }
    }

    fun updateBook(book: Books) {
        viewModelScope.launch {
            bookRepository.update(book)
        }
    }
}

data class HomeUiState(
    val bookList: List<Books>
)
