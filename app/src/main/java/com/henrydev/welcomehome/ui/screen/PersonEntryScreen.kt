package com.henrydev.welcomehome.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.welcomehome.AppViewModelProvider

@Composable
fun PersonEntryScreen(
    modifier: Modifier = Modifier,
    viewModel: PersonEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState: PersonUiState = viewModel.uiState
    Scaffold { innerPadding ->
        PersonEntryBody(
            uiState = uiState,
            onPersonValueChange = { viewModel.updateUiState(it) },
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
            )
                .padding(16.dp)
        )
    }

}

@Composable
fun PersonEntryBody(
    uiState: PersonUiState,
    onPersonValueChange: (PersonDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        PersonInputForm(
            personDetail = uiState.personDetail,
            onValueChange = onPersonValueChange
        )
    }
}


@Composable
fun PersonInputForm(
    personDetail: PersonDetail,
    onValueChange: (PersonDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = personDetail.firstName,
            onValueChange = { onValueChange(personDetail.copy( firstName = it )) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = personDetail.lastName,
            onValueChange = { onValueChange(personDetail.copy( lastName = it )) },
            label = { Text("Lastname") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = personDetail.cellphone,
            onValueChange = { onValueChange(personDetail.copy( cellphone = it)) },
            label = { Text("Cellphone") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = personDetail.residentialComplex,
            onValueChange = { onValueChange(personDetail.copy( residentialComplex = it)) },
            label = { Text("Residential complex") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}










