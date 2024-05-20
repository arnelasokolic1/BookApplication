package com.example.application.viewModel


import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.application.model.models.Books

data class BooksDetails(
    val id: Int = 0,
    val name: String = "",
    val author: String = "",
    val description: String = ""
)

data class BooksUiState(
    val booksDetails: BooksDetails = BooksDetails(),
    val isEntryValid: Boolean = false
)

fun BooksDetails.toBooks(): Books = Books(
    id = id,
    name = name,
    author = author,
    description= description
)

fun Books.toBooksDetails() = BooksDetails(
    id = id,
    name = name,
    author = author,
    description= description
)

fun Books.toBookUiState(isEntryValid: Boolean = false): BooksUiState = BooksUiState(
    booksDetails = this.toBooksDetails(),
    isEntryValid = isEntryValid
)