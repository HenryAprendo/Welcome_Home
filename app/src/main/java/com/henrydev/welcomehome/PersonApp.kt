package com.henrydev.welcomehome

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.henrydev.welcomehome.ui.home.HomeScreen
import com.henrydev.welcomehome.ui.screen.PersonEntryScreen

@Composable
fun PersonApp(
    modifier: Modifier = Modifier
) {
    HomeScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(
            text = "Entry person",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        ) },
        modifier = modifier
    )
}






