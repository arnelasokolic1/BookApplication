// RegisterScreen.kt
package com.example.application.ui.screens

import android.content.Context
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

object RegistrationDestination : NavigationDestination {
    override val route = "registration"
    override val title = "Register"
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))

}

@Composable
fun RegistrationScreenWithTopBar(
    context: Context,
    navigateToLogin: () -> Unit,
    navigateToProfilePage: (Int) -> Unit
) {
    Scaffold(
        topBar = { UserAppBar(titleScreen = RegistrationDestination.title, canNavigateBack = false) }
    ) { innerPadding ->
        RegisterScreen(
            context = context,
            navigateToLogin = navigateToLogin,
            navigateToProfilePage = navigateToProfilePage,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun RegisterScreen(
    context: Context? = null,
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToLogin: () -> Unit,
    navigateToProfilePage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

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
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome, Register!", color = Color.White,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, fontSize = 27.sp),
            modifier = Modifier.padding(bottom = 36.dp) ,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(18.dp))
        TextField(
            value = name,
            onValueChange = { name = it; viewModel.updateUiState(viewModel.usersUiState.usersDetails.copy(name = it)) },
            enabled = true,
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Name") },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = surname,
            onValueChange = { surname = it; viewModel.updateUiState(viewModel.usersUiState.usersDetails.copy(surname = it)) },
            enabled = true,
            label = { Text(text = "Surname") },
            placeholder = { Text(text = "Surname") },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it; viewModel.updateUiState(viewModel.usersUiState.usersDetails.copy(email = it)) },
            enabled = true,
            label = { Text(text = "Email", color = Color.DarkGray) },
            placeholder = { Text(text = "example@example.com") },
            isError = !checkEmail(email),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it; viewModel.updateUiState(viewModel.usersUiState.usersDetails.copy(password = it)) },
            enabled = true,
            label = { Text(text = "Password", color = Color.DarkGray) },
            placeholder = { Text(text = "Password") },
            isError = !checkPassword(password),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    if (viewModel.register()) {
                        Log.d("register", viewModel.usersUiState.toString())
                        navigateToProfilePage(viewModel.usersUiState.usersDetails.id)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF394289)),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .shadow(8.dp, RoundedCornerShape(50)),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "REGISTER", color = Color.White)
        }

        Spacer(modifier = Modifier.height(5.dp))
        TextButton(
            onClick = { navigateToLogin() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Already have an account?", color = Color.White
            )
        }
    }
}

fun checkEmail(email: String): Boolean {
    return EMAIL_ADDRESS.matcher(email).matches()
}

fun checkPassword(password: String): Boolean {
    return password.length >= 8
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(
            navigateToLogin = {},
            navigateToProfilePage = {}
        )
    }
}