package com.example.application

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.ui.screens.MyTheme
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.application.viewModel.AddBookViewModel
import com.example.application.viewModel.AppViewModelProvider
import kotlinx.coroutines.launch

object AddBookDestination : NavigationDestination {
    override val route = "add_book"
    override val title = "Add Book"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddBookBar(
    navigateToRegister: () -> Unit,
    navigateToAdminDashboard: (Int) -> Unit,
    navigateToUserDashboard: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = { UserAppBar(titleScreen = AddBookDestination.title, canNavigateBack = true, navigateBack = navigateBack) }
    ) {
        AddBookScreen(navigateToUserDashboard = navigateToUserDashboard, navigateToAdminDashboard = navigateToAdminDashboard, navigateToRegister = navigateToRegister)
    }
}

@Composable
fun AddBookScreen(
    viewModel: AddBookViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToUserDashboard: () -> Unit,
    navigateToAdminDashboard: (Int) -> Unit,
    navigateToRegister: () -> Unit
) {
    var bookName by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.booksUiState
    val detailsState = uiState.booksDetails
    var showNotification by remember { mutableStateOf(false) }
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = MyTheme.GradientColors,
                    startY = 0f,
                    endY = 1000f
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Adjusted padding
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add Book",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            TextField(
                value = bookName,
                onValueChange = { bookName = it; viewModel.updateUiState(detailsState.copy(name = it)) },
                label = { Text("Book Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = author,
                onValueChange = { author = it; viewModel.updateUiState(detailsState.copy(author = it)) },
                label = { Text("Author") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = description,
                onValueChange = { description = it; viewModel.updateUiState(detailsState.copy(description = it)) },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.register()
                        showNotification = true
                        // Delay to hide the notification after a few seconds
                        kotlinx.coroutines.delay(3000)
                        showNotification = false
                    }
                },
                colors = ButtonDefaults.buttonColors(MyTheme.Blue),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .shadow(8.dp, RoundedCornerShape(50))
            ) {
                Text(text = "ADD BOOK", color = Color.White)
            }
        }

        if (showNotification) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp), // Adjust this value as needed
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
                    Text("Book added successfully!")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddBook() {
    MaterialTheme {
        AddBookBar(
            navigateToRegister = {},
            navigateToAdminDashboard = {},
            navigateToUserDashboard = {},
            navigateBack = {}
        )
    }
}
