package com.example.application.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.models.Books
import com.example.application.model.repositories.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserHomeViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState(emptyList()))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            bookRepository.getBooks().collect { books ->
                _homeUiState.value = HomeUiState(books)
            }
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
