package com.example.mobiledeveloping

import MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    var showSnackbar by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My First App") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(10.dp)
            ) {
                IconButton(onClick = { viewModel.onCalendarClick() }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Календарь")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    viewModel.onHomeClick()
                }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Главная")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    viewModel.onProfileClick()
                }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Профиль")
                }
            }
        }
    ) { paddingValues ->
        if (showSnackbar) {
            Snackbar(
                action = {
                    Button(onClick = { showSnackbar = false }) {
                        Text("Закрыть")
                    }
                }
            ) {
                Text("Это Snackbar сообщение!")
            }
        }

        when {
            viewModel.isProfileClicked.value -> Profile(paddingValues, navController)
            viewModel.isHomeClicked.value -> ChatList(paddingValues, navController, { showSnackbar = true })
            viewModel.isCalendarClicked.value -> Calendar(paddingValues, navController)
            else -> ChatList(paddingValues, navController, { showSnackbar = true })
        }
    }
}

@Composable
fun ChatList(paddingValues: PaddingValues, navController: NavController, onChatClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(10) { index ->
                ChatItem(index) {
                    navController.navigate("chat/$index")
                    onChatClick()
                }
            }
        }
    }
}

@Composable
fun ChatItem(index: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Chat number $index",
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp
            )
            Text(
                text = "That a simple example for chat number $index. I just want to see this text on two lines so that you can try to make a restriction",
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun Profile(paddingValues: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text(text = "Hello, Dear User!")
        Row {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "avatar",
                modifier = Modifier
                    .padding(10.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray))
            Column {
                Text(text = "Dmitrii Platonov", modifier = Modifier.padding(10.dp))
                Text(text = "18 years old", modifier = Modifier.padding(7.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(paddingValues: PaddingValues, navController: NavController) {
    val datePickerState = rememberDatePickerState()
    DatePicker(
        modifier = Modifier.padding(paddingValues),
        state = datePickerState,
        showModeToggle = false
    )
}

@Preview
@Composable
private fun preview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}

