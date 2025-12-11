package com.henrydev.welcomehome.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.welcomehome.AppViewModelProvider
import com.henrydev.welcomehome.EditPersonTopAppBar
import com.henrydev.welcomehome.PersonTopAppBar
import com.henrydev.welcomehome.R
import com.henrydev.welcomehome.data.Person
import com.henrydev.welcomehome.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object PersonDetailDestination: NavigationDestination {
    override val route: String = "person_detail"
    override val titleRes: Int = R.string.details
    val itemIdArg = "itemId"
    val routeWithArg = "$route/{$itemIdArg}"
}

@Composable
fun PersonDetailScreen(
    navigateUp: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PersonDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val detailUiState by viewModel.detailUiState.collectAsState()
    var requestDeletePerson by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            EditPersonTopAppBar(
                title = stringResource(PersonDetailDestination.titleRes),
                onNavigateBack = navigateUp,
                onDelete = { requestDeletePerson = true },
                onUpdate = { }
            )
        },
        modifier = modifier
    ) { innerPadding ->

        val coroutineScope = rememberCoroutineScope()

        PersonDetailBody(
            uiState = detailUiState,
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .padding(16.dp)
        )

        if (requestDeletePerson) {
            DeleteConfirmationDialog(
                onCancel = { requestDeletePerson = false },
                onConfirm = {
                    coroutineScope.launch { viewModel.deletePerson() }
                    requestDeletePerson = false
                    navigateBack()
                }
            )
        }

    }
}

@Composable
fun PersonDetailBody(
    uiState: DetailUiState,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        PersonDetailCard(
            person = uiState.person
        )
    }
}


@Composable
fun PersonDetailCard(
    person: Person,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            PersonDetailRow(
                labelResId = R.string.label_name,
                content = person.firstName
            )
            HorizontalDivider()
            PersonDetailRow(
                labelResId = R.string.label_last_name,
                content = person.lastName
            )
            HorizontalDivider()
            PersonDetailRow(
                labelResId = R.string.label_cellphone,
                content = person.cellphone.toString()
            )
            HorizontalDivider()
            PersonDetailRow(
                labelResId = R.string.label_residential,
                content = person.residentialComplex
            )
            HorizontalDivider()
            PersonDetailRow(
                labelResId = R.string.label_type,
                content = "member"
            )
        }
    }
}


@Composable
fun PersonDetailRow(
    @StringRes labelResId: Int,
    content: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = stringResource(labelResId),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = content,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun DeleteConfirmationDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onCancel() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text("Dismiss")
            }
        },
        title = { Text("Confirmation") },
        text = { Text("Are you sure you want to delete this person?") },
        modifier = modifier
    )
}















