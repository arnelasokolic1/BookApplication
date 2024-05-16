package com.example.application

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.MyTheme

@Composable
fun AboutUs() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Add the image above the text
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(10.dp), // Add padding to create a frame effect
            contentAlignment = Alignment.Center
        ) {
            // Adding a circular frame around the image
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(shape = CircleShape)
                    .border(5.dp, MyTheme.LightPurple, shape = CircleShape) // Purple border
            ) {
                Image(
                    painter = painterResource(id = R.drawable.slika11),
                    contentDescription = "First photo",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Text(
            text = "About Us",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Welcome to BookLore, your gateway to the world of books! BookLore is an app designed to make your reading experience enjoyable and convenient. With BookLore, you can discover a vast collection of books spanning various genres, from classics to contemporary bestsellers.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp)) // Adding space between fields

        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple), // Use the custom pink color
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Start Reading", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AboutUs()
}
