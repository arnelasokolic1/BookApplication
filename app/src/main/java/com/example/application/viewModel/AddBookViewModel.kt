package com.example.application.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.application.model.repositories.BookRepository
import com.example.application.model.repositories.UserRepository

class AddBookViewModel(private val bookRepository: BookRepository): ViewModel() {
    /**
     * Holds current item ui state BACKEND
     */
    var booksUiState by mutableStateOf(BooksUiState())
        private set

    suspend fun register(): Boolean {
        // if(validateInput()){
        bookRepository.insert(booksUiState.booksDetails.toBooks())
        return true
        // }else return false
    }

    fun updateUiState(bookDetails: BooksDetails) {
        booksUiState =
            BooksUiState(booksDetails = bookDetails, isEntryValid = false)
    }

}


