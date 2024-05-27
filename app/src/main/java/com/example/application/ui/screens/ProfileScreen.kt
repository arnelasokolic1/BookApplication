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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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


object ProfileDestination: NavigationDestination {
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
    var uiState = viewModel.usersUiState
    var detailsState = uiState.usersDetails


    var name = "Naida"
    var surname = "Fatic"
    var email = "naida@gmail.com"
    var password = "123456789"
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Log.d("profile", viewModel.usersUiState.toString())

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
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

        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))
        Row {
            TextField(
                value = viewModel.usersUiState.usersDetails.name,
                onValueChange = {},
                label = {
                    Text(text = "name")
                },
                isError = false,
                readOnly = true,
                enabled = false,
                modifier = Modifier.width(135.dp),
                colors = TextFieldDefaults.colors(
                )
            )
            Spacer(modifier = Modifier.size(height = 0.dp, width = 5.dp))
            TextField(
                value = viewModel.usersUiState.usersDetails.surname,
                onValueChange = {},
                label = {
                    Text(text = "surname")
                },
                isError = false,
                readOnly = true,
                modifier = Modifier.width(135.dp)
            )
        }
        Spacer(modifier = Modifier.size(height = 10.dp, width = 0.dp))
        TextField(
            value = viewModel.usersUiState.usersDetails.email,
            onValueChange = {},
            label = {
                Text(text = "email")
            },
            isError = false,
            readOnly = true
        )
        Spacer(modifier = Modifier.size(height = 10.dp, width = 0.dp))

        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    val i = Intent(Intent.ACTION_SEND)
                    val emailAddress = arrayOf(email)
                    i.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                    i.setType("message/rfc822")
                    try {
                        context.startActivity(Intent.createChooser(i, "Choose an Email client : "))
                    } catch (s: SecurityException) {
                        Toast
                            .makeText(context, "An error occurred", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                .align(Alignment.Start)
        ) {

        }

        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileScreen()
}