package com.example.application.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.LoginRegistrationViewModel
import com.example.myapplication.R
import kotlinx.coroutines.launch

object LoginDestination: NavigationDestination {
    override val route = "login"
    override val title = "Login"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreenWithTopBar(
    navigateToRegister: () -> Unit,
    navigateToAdmin: (Int) -> Unit,
    navigateToUserDashboard: (Int) -> Unit,
){
    Scaffold(
        topBar = { UserAppBar(titleScreen = LoginDestination.title, canNavigateBack = false)}
    ) {
        LoginScreen(navigateToUserDashboard = navigateToUserDashboard, navigateToAdmin = navigateToAdmin, navigateToRegister = navigateToRegister)
    }
}

@Composable
fun LoginScreen(viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
                navigateToUserDashboard: (Int) -> Unit,
                navigateToAdmin: (Int) -> Unit,
                navigateToRegister: () -> Unit,
) {
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
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()

        val uiState = viewModel.usersUiState
        val detailsState = uiState.usersDetails

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Adding the image above the "Welcome back, Login!" text
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(10.dp), // Adding padding to create a frame effect
                contentAlignment = Alignment.Center
            ) {
                // Adding a circular frame around the image
                Box(
                    modifier = Modifier
                        .size(150.dp) // Adjust the size as needed
                        .clip(CircleShape) // Making the image and border circular
                        .border(8.dp, MyTheme.LightPurple, CircleShape)
                ) {
                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.slika20),
                        contentDescription = "Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                text = "Welcome back, Login!", color = Color.White,
                style = MaterialTheme.typography.bodyLarge
                    .copy(fontSize = 27.sp, fontWeight = FontWeight.Bold), // Bold and fontSize 28.sp
                modifier = Modifier.padding(bottom = 18.dp),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(16.dp)) // Adding space between fields
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    viewModel.updateUiState(detailsState.copy(email = it))
                },
                enabled = true,
                label = { Text(text = "email") },
                placeholder = { Text(text = "example@exmaple.com") },
                isError = false
            )

            Spacer(modifier = Modifier.height(8.dp)) // Adding space between fields
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    viewModel.updateUiState(detailsState.copy(password = it))
                },
                label = { Text(text = "password") }
            )
            Spacer(modifier = Modifier.height(16.dp)) // Adding space between fields
            Button(
                onClick = {
                    coroutineScope.launch {
                        Log.d("pre login", viewModel.usersUiState.toString())
                        if (viewModel.login()) {
                            Log.d("login", viewModel.usersUiState.toString())
                            if (viewModel.usersUiState.usersDetails.role == 1) {
                                navigateToAdmin(viewModel.usersUiState.usersDetails.id)
                            } else {
                                navigateToUserDashboard(viewModel.usersUiState.usersDetails.id)
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MyTheme.Blue), // Use the custom blue color
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .shadow(8.dp, RoundedCornerShape(50)),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "LOGIN", color = Color.White)
            }
            Spacer(modifier = Modifier.height(5.dp)) // Adding space between button and text
            TextButton(
                onClick = { navigateToRegister() },
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text(
                    text = "Don't have an account yet?", color = Color.White
                )
            }
        }
    }
}

@Composable
fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.Gray) },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(15.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        // LoginScreen()
    }
}
