package com.example.application.ui.screens

import androidx.compose.foundation.layout.*
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
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.UserHomeViewModel
import com.example.application.viewModel.UserViewModel

@Composable
fun UserDashboard(
    onAboutUsClick: () -> Unit,
    onBooksClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: UserHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState() /* poziv funkcije iz UserHomeViewModel */

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp)) // Add space between User Dashboard and text
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
    }


    LazyRow {
        items(homeUiState.bookList) {

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

