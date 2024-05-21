package com.example.application.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.model.models.Books
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.UserHomeViewModel

@Composable
fun BookItem(book: Books) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp),
       // elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = book.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Author: ${book.author}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${book.description}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun UserDashboard(
    onAboutUsClick: () -> Unit,
    onBooksClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: UserHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Hello Reader!",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 50.dp)
        )
        Button(
            onClick = { onAboutUsClick() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "About Us")
        }
        Button(
            onClick = { onLogoutClick() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Logout")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Books!",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),

            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(homeUiState.bookList) { book ->
                BookItem(book)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDashboardPreview() {
    MaterialTheme {
        UserDashboard({}, {}, {})
    }
}