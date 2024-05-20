package com.example.application.ui.screens

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.LoginRegistrationViewModel
import com.example.myapplication.R
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory)) {

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()  // sluzi da pozovemo ove funkcije suspend u drugoj klasi, drugoj ufnkciji ...
    var uiState = viewModel.usersUiState
    var detailsState = uiState.usersDetails

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome, Register!",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp)) // Add space between fields
        TextField(
            value = name,
            onValueChange = {  name = it;
                viewModel.updateUiState(detailsState.copy(name = it)) }, /* ovo moramo dodati, kako nase varijable kad se unose u bazu ne bi bile prazne
                npr. kad unesmeo ime-  da se to ime ispise, u suprotnom bi bilo prazno jer smo po default stavili name da je tipa Stirng = " " (prazno)
                u Users.kt */
            enabled = true,
            label = {
                Text(text = "name")
            },
            placeholder = {
                Text(text = "name")
            },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(8.dp)) // Add space between fields
        TextField(
            value = surname,
            onValueChange =  {surname = it;
                viewModel.updateUiState(detailsState.copy(surname = it)) },
            enabled = true,
            label = {
                Text(text = "surname")
            },
            placeholder = {
                Text(text = "surname")
            },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(8.dp)) // Add space between fields
        TextField(
            value = email,
            onValueChange = {email= it;
                viewModel.updateUiState(detailsState.copy(email = it)) },
            enabled = true,
            label = {
                Text(text = "email")
            },
            placeholder = {
                Text(text = "example@exmaple.com")
            },
            isError = !checkEmail(email),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(8.dp)) // Add space between fields

        TextField(
            value = password,
            onValueChange = {password = it;
                viewModel.updateUiState(detailsState.copy(password = it)) },
            enabled = true,
            label = {
                Text(text = "password")
            },
            placeholder = {
                Text(text = "password")
            },
              isError = !checkPassword(password),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next)
        )



        Spacer(modifier = Modifier.height(16.dp)) // Add space between fields
        Button(
            onClick = { checkEmail(email); coroutineScope.launch {
              viewModel.register();
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MyTheme.Purple), // Use the custom pink color
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "REGISTER", color = Color.White)
        }
        /* dodati poruku invalid email or pass */
        Spacer(modifier = Modifier.height(5.dp)) // Add space between button and text
        Text(
            text = "Already have an account?",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Icon(painter = painterResource(id = R.drawable.baseline_visibility_24), contentDescription = null)
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)

    }
}



fun checkEmail(email: String): Boolean{
    return  EMAIL_ADDRESS.matcher(email).matches()
}

fun checkPassword(password: String): Boolean {
    return password.length >= 8
}

@Composable
fun CustomRoundedTextField( // Renamed to avoid conflict
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
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}
