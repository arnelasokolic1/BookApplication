package com.example.application.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.application.ui.screens.navigation.NavigationDestination

object AboutUsDestination : NavigationDestination {
    override val route = "about_us"
    override val title = ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navigateBack: () -> Unit) {
    val faqList = remember { generateFAQs() }
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = AboutUsDestination.title,
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
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
                .padding(innerPadding) // Add innerPadding from Scaffold
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "FAQ",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(faqList) { index, faq ->
                        FAQItem(faq = faq)
                        if (index < faqList.size - 1) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }

                Button(
                    onClick = navigateBack,
                    colors = ButtonDefaults.buttonColors(MyTheme.Blue),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .shadow(8.dp, RoundedCornerShape(50))
                ) {
                    Text(text = "Back to Dashboard", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun FAQItem(faq: FAQ) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = faq.question,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MyTheme.Blue,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (expanded) {
            Text(
                text = faq.answer,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

data class FAQ(val question: String, val answer: String)

fun generateFAQs(): List<FAQ> {
    return listOf(
        FAQ(
            question = "What is BookApp?",
            answer = "BookApp is an application where you can explore the books, check which books lastly came and see their description by clicking on the book."
        ),
        FAQ(
            question = "How can I sort books alphabetically?",
            answer = "You can sort books alphabetically by navigating by clicking on the 'Sort Alphabetically' icon."
        ),
        FAQ(
            question = "How can I sort books by newly added order? ",
            answer = "You can sort books by latest added by clicking 'New' icon."
        ),

        FAQ(
            question = "Can I receive notifications for new arrivals?",
            answer = "No, not yet. We are planning to implement this feature in a future update."
        ),
        FAQ(
            question = "Is BookApp available on multiple platforms?",
            answer = "Currently, BookApp is available on Android devices. Support for iOS and web platforms is planned for future releases."
        ),
        FAQ(
            question = "Who developed BookApp?",
            answer = "BookApp was developed by four students from International Burch University as a part of Mobile Programming course."
        ),
        FAQ(
            question = "How can I contact support?",
            answer = "For any issues or inquiries, please send an email to support@bookapp.com."
        )
    )
}
