package com.example.application.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.ui.screens.navigation.NavigationDestination
import com.example.application.ui.screens.navigation.UserAppBar
import com.example.application.viewModel.AppViewModelProvider
import com.example.application.viewModel.LoginRegistrationViewModel
import com.example.application.viewModel.UserViewModel
import com.example.myapplication.R
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

object ProfileDestination : NavigationDestination {
    override val route = "profile"
    override val title = "Profile"
    const val userIdArg = "userID"
    val routeWithArgs = "$route/{$userIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreenWithTopBar(
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            UserAppBar(titleScreen = ProfileDestination.title, canNavigateBack = true, navigateBack = navigateBack)
        }
    ) {
        ProfileScreen()
    }
}

@Composable
fun ProfileScreen(
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState = viewModel.usersUiState
    val detailsState = uiState.usersDetails

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val GradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF755A90))

    Log.d("profile", viewModel.usersUiState.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = GradientColors,
                    startY = 0f,
                    endY = 1000f
                )
            )
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(5.dp, MyTheme.Purple, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.admin1),
                        contentDescription = "Admin",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.size(30.dp))
            Row {
                TextField(
                    value = detailsState.name,
                    onValueChange = {},
                    label = {
                        Text(text = "Name")
                    },
                    isError = false,
                    readOnly = true,
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                    )
                )
                Spacer(modifier = Modifier.size(5.dp))
                TextField(
                    value = detailsState.surname,
                    onValueChange = {},
                    label = {
                        Text(text = "Surname")
                    },
                    isError = false,
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                value = detailsState.email,
                onValueChange = {},
                label = {
                    Text(text = "Email")
                },
                isError = false,
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(30.dp))

            Text(
                text = "Account successfully created",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold, fontSize = 21.sp),
                modifier = Modifier.padding(bottom = 12.dp),
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileScreen()
}
