package com.henrydev.welcomehome.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.welcomehome.data.PersonsRepository
import com.henrydev.welcomehome.data.RolesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class EditPersonViewModel(
    saveStateHandle: SavedStateHandle,
    private val personsRepository: PersonsRepository,
    private val rolesRepository: RolesRepository
) : ViewModel() {

    val itemId: Int = checkNotNull(saveStateHandle[EditPersonDestination.itemIdArg])

    var uiState: PersonUiState by mutableStateOf(PersonUiState())
        private set

    init {
        //loadRoles()
        loadValues()
    }

    private fun loadRoles() {
        viewModelScope.launch {
            rolesRepository.getAllRolesStream()
                .map { RolesUiState(it) }
                .collect { rolesUiState ->
                    uiState = uiState.copy(rolesState = rolesUiState)
                }
        }
    }

    private fun loadValues() {
        viewModelScope.launch {
            rolesRepository.getAllRolesStream()
                .map { RolesUiState(it) }
                .collect { rolesUiState ->
                    uiState = personsRepository.getPersonStream(itemId)
                        .filterNotNull()
                        .first()
                        .toPersonUiState(
                            isEntryValid = true,
                            rolesState = rolesUiState
                        )
                }
        }
    }

    fun setRolId(rolId: Int) {
        val updatePersonDetail = uiState.personDetail.copy(rolId = rolId)
        uiState = uiState.copy(
            personDetail = updatePersonDetail,
            isEntryValid = validate(updatePersonDetail)
        )
    }

    fun updateUiState(personDetail: PersonDetail) {
        uiState = uiState.copy(
            personDetail = personDetail,
            isEntryValid = validate(personDetail)
        )
    }

    suspend fun updatePerson() {
        if (validate()) {
            val editPerson = uiState.personDetail.toEntityPerson()
            personsRepository.updatePerson(editPerson)
        }
    }

    private fun validate(personDetail: PersonDetail = uiState.personDetail): Boolean {
        with(personDetail) {
            return firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    cellphone.isNotBlank() &&
                    cellphone.toLongOrNull() != null &&
                    residentialComplex.isNotBlank() &&
                    rolId > 0
        }
    }

}
