package com.example.application.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.application.model.models.Books
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.UserHomeViewModel
import com.example.myapplication.R

object UserDashboardDestination : NavigationDestination {
    override val route = "userdashboard"
    override val title = "User Dashboard"
    const val userIdArg = "userID"
    val routeWithArgs = "$route/{$userIdArg}"
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserDashboardWithTopBar(
    navigateToAddBook: () -> Unit,
    navigateToProfilePage: (Int) -> Unit,
    navigateToWelcomePage: () -> Unit,
    navigateToAdminUsersList: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = UserDashboardDestination.GradientColors,
                    startY = 0f,
                    endY = 1000f
                )
            )
            .padding(16.dp)
    )
    Scaffold(
        topBar = { UserAppBar(titleScreen = UserDashboardDestination.title, canNavigateBack = false) }
    ) {
        UserDashboard(
            navigateToProfilePage = navigateToProfilePage,
            navigateToAddBook = navigateToAddBook,
            navigateToWelcomePage = navigateToWelcomePage,
            navigateToAdminUsersList = navigateToAdminUsersList
        )
    }
}

@Composable
fun BookItem(
    book: Books, onDeleteClick: (Books) -> Unit, onUpdateClick: (Books) -> Unit,
    viewModel: UserHomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState = viewModel.usersUiState
    val detailsState = uiState.usersDetails

    Log.d("UserDashboard1", detailsState.toString())

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditBookDialog(book = book, onDismiss = { showDialog = false }, onUpdateClick = onUpdateClick)
    }

    Card(
        modifier = Modifier
            .padding(6.dp)
            .width(310.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)) // Adding shadow here
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
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(6.dp)
                    .width(310.dp),
            ) {
                if (detailsState.role == 1) {
                    Button(
                        onClick = { showDialog = true },
                        colors = ButtonDefaults.buttonColors(MyTheme.Blue),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .padding(vertical = 0.dp)
                            .shadow(8.dp, RoundedCornerShape(50))
                    ) {
                        Text(text = "EDIT")
                    }

                    Button(
                        onClick = { onDeleteClick(book) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = "DELETE")
                    }
                }
            }
        }
    }
}


@Composable
fun EditBookDialog(book: Books, onDismiss: () -> Unit, onUpdateClick: (Books) -> Unit) {
    var title by remember { mutableStateOf(book.name) }
    var author by remember { mutableStateOf(book.author) }
    var description by remember { mutableStateOf(book.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onUpdateClick(book.copy(name = title, author = author, description = description))
                onDismiss()
            }) {
                Text("Save", color = MyTheme.Purple)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = MyTheme.Red1)
            }
        },
        title = { Text(text = "Edit Book") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = author,
                    onValueChange = { author = it },
                    label = { Text("Author") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight(Alignment.CenterVertically)
    )
}

@Composable
fun UserDashboard(
    viewModel: UserHomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToProfilePage: (Int) -> Unit,
    navigateToAddBook: () -> Unit,
    navigateToWelcomePage: () -> Unit,
    navigateToAdminUsersList: () -> Unit
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val uiState = viewModel.usersUiState
    val detailsState = uiState.usersDetails
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = UserDashboardDestination.GradientColors,
                    startY = 0f,
                    endY = 1000f
                )
            )
            .padding(16.dp)
    )
    Log.d("UserDashboard1", detailsState.toString())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(

            text = "Books",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(homeUiState.bookList) { book ->
                BookItem(
                    book = book,
                    onDeleteClick = { bookToDelete -> viewModel.deleteBook(bookToDelete) },
                    onUpdateClick = { bookToUpdate -> viewModel.updateBook(bookToUpdate) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MyTheme.Blue, shape = RoundedCornerShape(0.dp))
                .border(width = 5.dp, color = MyTheme.LightPurple, shape = RoundedCornerShape(0.dp))
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (detailsState.role == 1) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_to_photos_24),
                        contentDescription = "Add Book",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(onClick = navigateToAddBook)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Icon(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "Logout",
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(onClick = navigateToWelcomePage)
                )
                Spacer(modifier = Modifier.width(16.dp))
                if (detailsState.role == 1) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_format_list_bulleted_24),
                        contentDescription = "Admin User List",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(onClick = navigateToAdminUsersList)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDashboardPreview() {
    MaterialTheme {
        UserDashboardWithTopBar(
            navigateToAddBook = {},
            navigateToProfilePage = {},
            navigateToWelcomePage = {},
            navigateToAdminUsersList = {}
        )
    }
}
