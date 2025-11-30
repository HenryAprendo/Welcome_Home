package com.henrydev.welcomehome.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.henrydev.welcomehome.data.Person
import com.henrydev.welcomehome.data.PersonsRepository
import com.henrydev.welcomehome.data.Rol
import com.henrydev.welcomehome.data.RolesRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PersonEntryViewModel(
    private val personsRepository: PersonsRepository,
    private val rolesRepository: RolesRepository
): ViewModel() {

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
        uiState = uiState.copy(
            personDetail = uiState.personDetail.copy(rolId = rolId)
        )
    }

    fun updateUiState(personDetail: PersonDetail) {
        uiState = PersonUiState(personDetail)
    }

    suspend fun insertPerson() {
        //personsRepository.insertPerson()
    }

}

data class PersonUiState(
    val personDetail: PersonDetail = PersonDetail(),
    val rolesState: RolesUiState = RolesUiState()
)

data class RolesUiState(
    val roles: List<Rol> = listOf()
)

data class PersonDetail(
    val firstName: String = "",
    val lastName: String = "",
    val cellphone: String = "",
    val residentialComplex: String = "",
    //val createdAt: Long = System.currentTimeMillis(),
    val rolId: Int = 0
)

fun PersonDetail.toEntityPerson() = Person(
    firstName = firstName,
    lastName = lastName,
    cellphone = cellphone.toIntOrNull() ?: 0,
    residentialComplex = residentialComplex,
    rolId = this.rolId
)
