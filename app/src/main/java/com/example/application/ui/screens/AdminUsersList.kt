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
import com.example.application.model.models.Users
import com.example.application.viewModel.AdminUsersListViewModel
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.HomeUiState
import com.example.application.viewModel.UserHomeViewModel

@Composable
fun UserItem(user: Users) {
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
                text = "Name: ${user.name}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Surname: ${user.surname}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Email: ${user.email}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Paaword: ${user.password}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun AdminUsersList(
    onAboutUsClick: () -> Unit,
    onBooksClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: AdminUsersListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val homeUiState by viewModel.homeUiState.collectAsState()



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Users!",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),

            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(homeUiState.userList) { user ->
                UserItem(user)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminUsersListViewModel() {
    MaterialTheme {
        AdminUsersList({},{},{})
    }
}
