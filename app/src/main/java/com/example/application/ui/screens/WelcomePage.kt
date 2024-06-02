package com.example.application.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.myapplication.R

// Define custom colors in your theme
object MyTheme {
    val DarkBlue = Color(0xFF1E3A8A)
    val Purple = Color(0xFF755A90) // Purple color
    val LightPurple = Color(0xFFC7B7D6)
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))
    val Blue = Color(0xFF39448A)
    val Red1 = Color(0xFFB0261A)
    val Purple1 = Color(0xFF73588C)
    val Blue1 = Color(0xFFCFE3F1)
}

object WelcomePageDestination : NavigationDestination {
    override val route = "welcome_page"
    override val title = ""
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePageWithTopBar(
    context: Context,
    navigateToLogin: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = WelcomePageDestination.title,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge // Adjust title text style if needed
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White // Set the TopAppBar color
                )
            )
        }
    ) { innerPadding ->
        WelcomePage(navigateToLogin = navigateToLogin, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun WelcomePage(navigateToLogin: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
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
                // Adding a rectangular frame around the image
                Box(
                    modifier = Modifier
                        .size(255.dp)
                        .clip(shape = RoundedCornerShape(24.dp)) // Use RoundedCornerShape for a different shape
                        .border(8.dp, MyTheme.LightPurple, shape = RoundedCornerShape(24.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo3),
                        contentDescription = "First photo",
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f), // Maintain aspect ratio
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Adding text with a different font and blue color
            Text(
                text = "Welcome to BookApp",
                color = White,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    letterSpacing = 0.15.sp,
                    lineHeight = 38.sp,
                    fontFamily = FontFamily.Serif
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
                onClick = { navigateToLogin() },
                colors = ButtonDefaults.buttonColors(MyTheme.Blue),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .shadow(8.dp, RoundedCornerShape(50))
            ) {
                Text(text = "Start Reading", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MaterialTheme {
        WelcomePage(navigateToLogin = {})
    }
}
