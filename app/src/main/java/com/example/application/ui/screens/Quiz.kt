package com.example.application.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.application.ui.screens.navigation.NavigationDestination

object QuizDestination : NavigationDestination {
    override val route = "quiz"
    override val title = "Quiz App"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = QuizDestination.title, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF673AB7))
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90)),
                        startY = 0f,
                        endY = 1000f
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Welcome Message
                Text(
                    text = "Welcome back!",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Let's play!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                // Daily Strike Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFA47EE7),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "3 days strike!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = 0.75f,
                            color = Color(0xFFCE93D8),
                            trackColor = Color(0xFF9575CD),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "+10 daily points",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                // Quiz of the Week Section
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF673AB7),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Quiz of the Week",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Design Tools",
                            fontSize = 16.sp,
                            color = Color.White,
                            textDecoration = TextDecoration.Underline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { /* Start Quiz */ }) {
                            Text(text = "Play now!")
                        }
                    }
                }

                // Categories Section
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Categories",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(16.dp))
                CategoryRow(categoryName = "Music", iconColor = Color(0xFF9575CD))
                Spacer(modifier = Modifier.height(8.dp))
                CategoryRow(categoryName = "Sports", iconColor = Color(0xFF9575CD))
                Spacer(modifier = Modifier.height(8.dp))
                CategoryRow(categoryName = "Art", iconColor = Color(0xFF9575CD))
            }
        }
    }
}

@Composable
fun CategoryRow(categoryName: String, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF512DA8),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = categoryName,
            fontSize = 18.sp,
            color = Color.White
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconColor, shape = RoundedCornerShape(50))
        )
    }
}

@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(navigateBack = {})
}
