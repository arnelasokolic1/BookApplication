package com.example.application.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserDashboard(
    onAboutUsClick: () -> Unit,
    onBooksClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
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
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "About Us")
        }
        Button(
            onClick = { onBooksClick() },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Books")
        }
        Button(
            onClick = { onLogoutClick() },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Logout")
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

