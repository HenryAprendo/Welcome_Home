package com.henrydev.welcomehome.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.welcomehome.AppViewModelProvider
import com.henrydev.welcomehome.PersonTopAppBar
import com.henrydev.welcomehome.R
import com.henrydev.welcomehome.ui.navigation.NavigationDestination

object PersonDetailDestination: NavigationDestination {
    override val route: String = "person_detail"
    override val titleRes: Int = R.string.details
    val itemIdArg = "itemId"
    val routeWithArg = "$route/{$itemIdArg}"
}

@Composable
fun PersonDetailScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PersonDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            PersonTopAppBar(
                title = PersonDetailDestination.titleRes,
                onNavigateBack = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        PersonDetailBody(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PersonDetailBody(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text("Welcome to details person")
    }
}









