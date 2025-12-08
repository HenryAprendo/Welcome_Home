package com.henrydev.welcomehome.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.welcomehome.AppViewModelProvider
import com.henrydev.welcomehome.PersonTopAppBar
import com.henrydev.welcomehome.R
import com.henrydev.welcomehome.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.home_welcome
}

@Composable
fun HomeScreen(
    navigateToEntryPerson: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val size = homeUiState.persons.size
    Scaffold(
        topBar = {
            PersonTopAppBar(
                title = HomeDestination.titleRes,
                onNavigateBack = navigateBack,
                canNavigateBack = false
            ) },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToEntryPerson) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new person"
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->

        HomeBody(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
            )
        )
    }
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Welcome Home")
    }
}







