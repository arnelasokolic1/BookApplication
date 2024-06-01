package com.example.application.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.model.models.Users
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.application.viewModel.AdminUsersListViewModel
import com.example.application.viewModel.AppViewModelProvider
import com.example.myapplication.R

object AdminUsersListDestination: NavigationDestination {
    override val route = "admin_users_list"
    override val title = "Admin Users list"
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminUsersListWithTopBar(
    navigateToUserDashboard: () -> Unit,
    navigateBack: () -> Unit
){
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
    )
    Scaffold(
        topBar = { UserAppBar(titleScreen = AdminUsersListDestination.title, canNavigateBack = true, navigateBack = navigateBack) }
    ) {
        AdminUsersList(navigateToUserDashboard = navigateToUserDashboard)
    }
}

@Composable
fun UserItem(
    user: Users,
    onEditClick: (Users) -> Unit,
    onDeleteClick: (Users) -> Unit
) {
    Card(

        modifier = Modifier
            .padding(6.dp)
            .width(310.dp),

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Name: ${user.name}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Surname: ${user.surname}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Email: ${user.email}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Password: ${user.password}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {


                }
                Button(
                    onClick = { onDeleteClick(user) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF755A90)),
                    modifier = Modifier
                        .padding(vertical = 0.dp)
                        .shadow(15.dp, RoundedCornerShape(50)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "REGISTER", color = Color.White)
                }



            }
        }
    }


@Composable
fun AdminUsersList(
    navigateToUserDashboard: () -> Unit,

    viewModel: AdminUsersListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

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
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Users!",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(homeUiState.userList) { user ->
                UserItem(
                    user,
                    onEditClick = { /* Handle edit click here */ },
                    onDeleteClick = { viewModel.deleteUser(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminUsersListPreview() {
    MaterialTheme {
        AdminUsersList(navigateToUserDashboard = {})

    }
}