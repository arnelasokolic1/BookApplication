package com.example.application.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.myapplication.R

// Define custom colors in your theme
object MyTheme {

    val DarkBlue = Color(0xFF1E3A8A)
    val Purple = Color(0xFF755A90) // Purple color
    val LightPurple = Color(0xFFA699B3)
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))
}


object WelcomePageDestination: NavigationDestination {
    override val route = "welcome_page"
    override val title = ""
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomePageWithTopBar(
    context: Context,
    navigateToLogin: () -> Unit
) {
    Scaffold(
        topBar = { UserAppBar(titleScreen = WelcomePageDestination.title, canNavigateBack = false) }
    ) {
        WelcomePage(navigateToLogin = navigateToLogin)

    }

}

@Composable
fun WelcomePage(navigateToLogin: () -> Unit,
                modifier: Modifier = Modifier) {
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(10.dp), // Adding padding to create a frame effect
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
                    painter = painterResource(id = R.drawable.book5),
                    contentDescription = "First photo",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        // Adding text with a different font and blue color
        Text(
            text = "Welcome to BookLore",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Unlock the World of Books",
            color = Color.LightGray,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Button(
            onClick = { navigateToLogin()},
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .shadow(8.dp, RoundedCornerShape(50))
        ) {
            Text(text = "Start Reading", color = Color.White)
        }

    }
}}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MaterialTheme {
        WelcomePage(navigateToLogin = {})
    }
}