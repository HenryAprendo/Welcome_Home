package com.henrydev.welcomehome.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.welcomehome.AppViewModelProvider
import com.henrydev.welcomehome.PersonTopAppBar
import com.henrydev.welcomehome.R
import com.henrydev.welcomehome.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object EditPersonDestination: NavigationDestination {
    override val route: String = "edit_person"
    override val titleRes: Int = R.string.edit
    val itemIdArg: String = "itemId"
    val routeWithArg: String = "$route/{$itemIdArg}"
}

@Composable
fun EditPersonScreen(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPersonViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            PersonTopAppBar(
                title = stringResource(EditPersonDestination.titleRes),
                onNavigateUp = navigateUp,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        PersonEntryBody(
            uiState = uiState,
            onPersonValueChange = { viewModel.updateUiState(it) },
            onSetRolPerson = { rolId -> viewModel.setRolId(rolId) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePerson()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .padding(16.dp)
        )
    }
}
