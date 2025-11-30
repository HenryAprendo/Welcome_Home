package com.henrydev.welcomehome.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.welcomehome.AppViewModelProvider
import com.henrydev.welcomehome.PersonTopAppBar
import com.henrydev.welcomehome.data.Rol

@Composable
fun PersonEntryScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PersonEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState: PersonUiState = viewModel.uiState
    Scaffold(
        topBar = {
            PersonTopAppBar(
                title = "Entry Person",
                onNavigateBack = navigateBack,
            )
                 },
        modifier = modifier
    ) { innerPadding ->
        PersonEntryBody(
            uiState = uiState,
            onPersonValueChange = { viewModel.updateUiState(it) },
            onSetRolPerson = { rolId -> viewModel.setRolId(rolId) },
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
    onSetRolPerson: (Int) -> Unit,
    onPersonValueChange: (PersonDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        PersonInputForm(
            personDetail = uiState.personDetail,
            rolesList = uiState.rolesState.roles,
            onValueChange = onPersonValueChange,
            onSetRol = onSetRolPerson
        )
    }
}


@Composable
fun PersonInputForm(
    personDetail: PersonDetail,
    rolesList: List<Rol>,
    onSetRol: (Int) -> Unit,
    onValueChange: (PersonDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        var expanded by remember { mutableStateOf(false) }

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

        TextField(
            value = rolesList.find { it.rolId == personDetail.rolId }?.name ?: "Select a rol",
            onValueChange = { },
            label = { Text("Rol") },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Deploy roles",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            rolesList.forEach { (rolId, option) ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSetRol(rolId)
                        expanded = false
                    }
                )
            }
        }

    }
}










