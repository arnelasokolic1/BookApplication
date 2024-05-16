package com.example.application.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.application.R

@Composable
fun AdminDashboard(
    onAddBookClick: () -> Unit,
    onManageUsersClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp)) // Add space between Admin Dashboard and photo
        Text(
            text = "Hello Admin !",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 50.dp)
        )
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(5.dp, MyTheme.Purple, CircleShape) // Add a purple border
        ) {
            Image(
                painter = painterResource(id = R.drawable.slika10),
                contentDescription = "Picture",
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(32.dp)) // Add bigger space between photo and buttons
        Button(
            onClick = { onAddBookClick() },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Add Book")
        }
        Button(
            onClick = { onManageUsersClick() },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Users")
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
fun AdminDashboardPreview() {
    MaterialTheme {
        AdminDashboard({}, {}, {})
    }
}
