package com.example.application.ui.screens.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAppBar(
    titleScreen: String,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit = {},
    onAboutUsClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(titleScreen) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        /*actions = {
            TextButton(onClick = onAboutUsClick) {
              /*  Text(text = "About Us", color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 8.dp))  */

                Spacer(modifier = Modifier.size(width = 250.dp, height = 0.dp))
            }
            TextButton(onClick = onLogoutClick) {
           /*     Text(text = "Logout", color = MaterialTheme.colorScheme.onSecondaryContainer) */
            }
        } */
    )
}

@Preview(showBackground = true)
@Composable
fun UserAppBarPreview() {
    UserAppBar(titleScreen = "test", canNavigateBack = true, navigateBack = {}, onAboutUsClick = {}, onLogoutClick = {})
}
