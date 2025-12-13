package com.henrydev.welcomehome.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.welcomehome.data.Person
import com.henrydev.welcomehome.data.PersonsRepository
import com.henrydev.welcomehome.data.RolesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PersonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val rolesRepository: RolesRepository,
    private val personsRepository: PersonsRepository
) : ViewModel() {

    val itemId: Int = checkNotNull(savedStateHandle[PersonDetailDestination.itemIdArg])

//    val detailUiState: StateFlow<DetailUiState> = personsRepository.getPersonStream(itemId)
//        .filterNotNull()
//        .map { personDta ->
//            DetailUiState(personDta)
//        }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(TIME_OUT_MILLIS),
//            initialValue = DetailUiState()
//        )

    val detailUiState: StateFlow<DetailUiState> =
        combine(
            personsRepository.getPersonStream(itemId),
            rolesRepository.getAllRolesStream()
        ) { person, allRoles ->
            val nameRole = allRoles.find { it.rolId == person?.rolId }?.name ?: "Not found"
            DetailUiState(
                person = person ?: defaultPerson,
                role = nameRole
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIME_OUT_MILLIS),
            initialValue = DetailUiState()
        )

    companion object {
        const val TIME_OUT_MILLIS = 5_000L
    }

    suspend fun deletePerson() {
        personsRepository.deletePerson(detailUiState.value.person)
    }

}


data class DetailUiState(
    val person: Person = defaultPerson,
    val role: String = ""
)

val defaultPerson: Person = Person(
    personId = 0,
    firstName = "Jhon",
    lastName = "Due",
    cellphone = 123456789,
    residentialComplex = "residential",
    createdAt = 1000,
    rolId = 0
)



