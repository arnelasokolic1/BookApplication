package com.example.application

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.ui.screens.MyTheme
import com.example.application.viewModel.AddBookViewModel
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.LoginRegistrationViewModel
import kotlinx.coroutines.launch

@Composable
fun AddBookScreen(viewModel: AddBookViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    var bookName by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var uiState = viewModel.booksUiState
    var detailsState = uiState.booksDetails

    //val homeUiState by viewModel.bookUiState.collectAsState() - za prikazivanje

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add Book",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        TextField(
            value = bookName, //iz UI state - Books.kt
            onValueChange = { bookName = it; viewModel.updateUiState(detailsState.copy(name = it))  },
            label = { Text("Book Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        TextField(
            value = author,
            onValueChange = { author = it;
                viewModel.updateUiState(detailsState.copy(author= it)) },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        TextField(
            value = description,
            onValueChange = { description = it; viewModel.updateUiState(detailsState.copy(description = it))  },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )


        /* val downloadIcon = vectorResource(id = R.drawable.ic_download)
        Icon(
            imageVector = downloadIcon,
            contentDescription = "Download Icon",
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.size(24.dp)
        )*/



        Button(
            onClick = { coroutineScope.launch{viewModel.register()} },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Add Book", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddBook() {
    MaterialTheme {
        AddBookScreen()
    }
}