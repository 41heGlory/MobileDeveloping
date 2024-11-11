package com.example.mobiledeveloping

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ChatScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Chat Screen", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(Screens.MainScreen.screenName)
            }
        ) {
            Text(text = "Go to Main Screen")
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(navController = rememberNavController())
}