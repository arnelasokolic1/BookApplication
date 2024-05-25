package com.example.application.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.models.Books
import com.example.application.model.repositories.BookRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserHomeViewModel(private val bookRepository: BookRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = bookRepository.getBooks().map { HomeUiState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HomeUiState()
    )

    fun deleteBook(book: Books) {
        viewModelScope.launch {
            bookRepository.delete(book)
        }
    }
}

data class HomeUiState(val bookList: List<Books> = emptyList())
