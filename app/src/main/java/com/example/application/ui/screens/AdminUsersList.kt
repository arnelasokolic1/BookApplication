package com.example.application.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.model.models.Users
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.application.viewModel.AdminUsersListViewModel
import com.example.application.viewModel.AppViewModelProvider
import com.example.myapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AdminUsersListDestination : NavigationDestination {
    override val route = "admin_users_list"
    override val title = "Admin Users list"
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminUsersListWithTopBar(
    navigateToUserDashboard: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            UserAppBar(
                titleScreen = AdminUsersListDestination.title,
                canNavigateBack = true,
                navigateBack = navigateBack
            )
        }
    ) {
        AdminUsersList(navigateToUserDashboard = navigateToUserDashboard)
    }
}

@Composable
fun UserItem(
    user: Users,
    onEditClick: (Users) -> Unit,
    onDeleteClick: (Users) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(8.dp)), // Added shadow with RoundedCornerShape
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
                text = "Password: ${user.password}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onDeleteClick(user) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .padding(vertical = 0.dp)
                        .shadow(15.dp, RoundedCornerShape(50)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "DELETE", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun AdminUsersList(
    navigateToUserDashboard: () -> Unit,
    viewModel: AdminUsersListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var showNotification by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    var isSortedAlphabetically by remember { mutableStateOf(false) }
    var showLatestUsers by remember { mutableStateOf(false) }

    val filteredUsers = homeUiState.userList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.surname.contains(searchQuery, ignoreCase = true) ||
                it.email.contains(searchQuery, ignoreCase = true)
    }.let { users ->
        if (isSortedAlphabetically) {
            users.sortedBy { it.name }
        } else if (showLatestUsers) {
            users.sortedByDescending { it.id } // Assuming 'id' can represent the order of addition
        } else {
            users
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = AdminUsersListDestination.GradientColors,
                    startY = 0f,
                    endY = 1000f
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Users!",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search Users ") },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth() // Fill the width of the row
                        .border(
                            width = 2.dp,
                            color = MyTheme.LightPurple,
                            shape = RoundedCornerShape(0.dp)
                        )
                )

                IconButton(onClick = { isSortedAlphabetically = !isSortedAlphabetically }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_sort_by_alpha_24),
                        contentDescription = "Sort Alphabetically",
                        tint = Color.White
                    )
                }

                IconButton(onClick = { showLatestUsers = !showLatestUsers }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_fiber_new_24),
                        contentDescription = "Show Latest Users",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredUsers.isEmpty()) {
                Text(
                    text = "No users found",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(filteredUsers) { user ->
                        UserItem(
                            user,
                            onEditClick = { /* Handle edit click here */ },
                            onDeleteClick = { userToDelete ->
                                viewModel.deleteUser(userToDelete)
                                notificationMessage = "${userToDelete.name} is deleted successfully"
                                showNotification = true
                                coroutineScope.launch {
                                    delay(3000)
                                    showNotification = false
                                }
                            }
                        )
                    }
                }
            }
        }

        if (showNotification) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp), // Adjusted to be higher
                contentAlignment = Alignment.BottomCenter
            ) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    containerColor = MyTheme.Blue,
                    action = {
                        TextButton(onClick = { showNotification = false }) {
                            Text("Close", color = Color.White)
                        }
                    }
                ) {
                    Text(notificationMessage)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminUsersListPreview() {
    MaterialTheme {
        AdminUsersList(navigateToUserDashboard = {})
    }
}
