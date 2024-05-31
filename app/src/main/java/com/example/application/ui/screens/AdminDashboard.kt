package com.example.application.ui.screens

import android.annotation.SuppressLint
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
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.myapplication.R


object AdminDashboardDestination: NavigationDestination {
    override val route = "admin_dashboard"
    override val title = ""
    //dodati argumente
    const val userIdArg = "userID"
    val routeWithArgs = "$route/{$userIdArg}"
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminDashboardScreenWithTopBar(
    navigateToAddBook: () -> Unit,
    navigateToProfilePage: (Int) -> Unit,
    navigateToUserDashboard: () -> Unit,
){
    Scaffold(
        topBar = { UserAppBar(titleScreen = AdminDashboardDestination.title, canNavigateBack = false) }
    ) //{
    // LoginScreen(navigateToRegister = navigateToRegister, navigateToProfilePage = navigateToProfilePage)
    //}

    {
        AdminDashboard(navigateToUserDashboard = navigateToUserDashboard  , navigateToProfilePage = navigateToProfilePage, navigateToAddBook = navigateToAddBook )


    }
}

@Composable
fun AdminDashboard( navigateToAddBook: () -> Unit,
                    navigateToUserDashboard: () -> Unit,
                    navigateToProfilePage:  (Int) -> Unit
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
            onClick = { navigateToAddBook() },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Add Book")
        }
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Users")
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Logout")
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(MyTheme.Purple),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Books")
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