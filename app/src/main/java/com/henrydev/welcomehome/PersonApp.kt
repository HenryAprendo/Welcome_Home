package com.henrydev.welcomehome

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import com.henrydev.welcomehome.ui.navigation.PersonNavHost

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
    title: String,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        Icons.Default.ArrowBack,
                        "go to back"
                    )
                }
            }
        },
        modifier = modifier
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPersonTopAppBar(
    title: String,
    onNavigateUp: () -> Unit,
    onDelete: () -> Unit,
    onNavigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
) {
    TopAppBar(
        title = { Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        Icons.Default.ArrowBack,
                        "go to back"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { onDelete() }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete person"
                )
            }
            IconButton(onClick = { onNavigateToEdit() }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit person"
                )
            }
        },
        modifier = modifier
    )
}








