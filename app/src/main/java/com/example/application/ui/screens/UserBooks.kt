package com.example.application.ui.screens/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Book(
    val name: String,
    val author: String,
    val description: String
)

@Composable
fun BooksScreen(books: List<Book>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Books",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        /*
        ScrollableBooks(books = books)
    }*/
    } }

    @Composable
    fun ScrollableBooks(books: List<Book>) {
        val scrollState = rememberScrollState()


            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(end = 12.dp)

            ) {
                books.forEach { book ->
                    BookItem(book = book)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

    }

    @Composable
    fun BookItem(book: Book) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(BorderStroke(1.dp, Color.Gray), MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Name: ${book.name}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Author: ${book.author}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Description: ${book.description}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                /*Icon for downloading
            val downloadIcon = vectorResource(id = R.drawable.ic_download)
            Icon(
                imageVector = downloadIcon,
                contentDescription = "Download Icon",
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.size(24.dp)
            )*/
            }
        }
    }
}
    @Preview(showBackground = true)
    @Composable
    fun UserBooksPreview() {
        val books = listOf(
            Book("Book 1", "Author 1", "Description 1"),
            Book("Book 2", "Author 2", "Description 2"),
            Book("Book 3", "Author 3", "Description 3"),
            Book("Book 4", "Author 4", "Description 4"),
            Book("Book 5", "Author 5", "Description 5"),
            Book("Book 6", "Author 6", "Description 6"),
            Book("Book 7", "Author 7", "Description 7"),
            Book("Book 8", "Author 8", "Description 8"),
        )

        BooksScreen(books = books)
    }
}

 */
