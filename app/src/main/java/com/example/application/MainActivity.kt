package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.application.ui.screens.AdminDashboard
import com.example.application.ui.theme.APPLICATIONTheme
import com.example.application.ui.screens.LoginScreen
import com.example.application.ui.screens.ProfileScreen
import com.example.application.ui.screens.RegisterScreen
import com.example.application.ui.screens.UserDashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APPLICATIONTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    // You can directly call the WelcomePage composable here
    UserDashboard({}, {}, {})
}


