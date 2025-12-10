package com.henrydev.welcomehome.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.welcomehome.data.Person
import com.henrydev.welcomehome.data.PersonsRepository
import com.henrydev.welcomehome.data.Rol
import com.henrydev.welcomehome.data.RolesRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PersonEntryViewModel(
    private val personsRepository: PersonsRepository,
    private val rolesRepository: RolesRepository
) : ViewModel() {

    var uiState by mutableStateOf(PersonUiState())
        private set

    init {
        loadRoles()
    }

    private fun loadRoles() {
        viewModelScope.launch {
            rolesRepository.getAllRolesStream()
                .map { RolesUiState(it) }
                .collect { rolesState ->
                    uiState = uiState.copy(rolesState = rolesState)
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

    suspend fun insertPerson() {
        if (validate()) {
            val newPerson = uiState.personDetail.toEntityPerson()
            personsRepository.insertPerson(newPerson)
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

data class PersonUiState(
    val personDetail: PersonDetail = PersonDetail(),
    val rolesState: RolesUiState = RolesUiState(),
    val isEntryValid: Boolean = false
)

data class RolesUiState(
    val roles: List<Rol> = listOf()
)

data class PersonDetail(
    val firstName: String = "",
    val lastName: String = "",
    val cellphone: String = "",
    val residentialComplex: String = "",
    val rolId: Int = 0
)

fun PersonDetail.toEntityPerson() = Person(
    firstName = firstName,
    lastName = lastName,
    cellphone = cellphone.toLong(),
    residentialComplex = residentialComplex,
    rolId = rolId
)


