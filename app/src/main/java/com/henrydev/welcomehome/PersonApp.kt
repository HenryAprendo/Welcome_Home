package com.henrydev.welcomehome

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import com.henrydev.welcomehome.ui.home.HomeScreen
import com.henrydev.welcomehome.ui.navigation.PersonNavHost
import com.henrydev.welcomehome.ui.screen.PersonEntryScreen

@Composable
fun PersonApp(
    modifier: Modifier = Modifier
) {
    val controller = rememberNavController()
    PersonNavHost(controller)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonTopAppBar(
    title: String = "change",
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    Icons.Default.ArrowBack,
                    "go to back"
                )
            }
        },
        modifier = modifier
    )
}






