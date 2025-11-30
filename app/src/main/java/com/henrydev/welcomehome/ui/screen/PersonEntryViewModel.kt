package com.henrydev.welcomehome.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.henrydev.welcomehome.data.Person
import com.henrydev.welcomehome.data.PersonsRepository

class PersonEntryViewModel(
    private val personsRepository: PersonsRepository
): ViewModel() {

    var uiState by mutableStateOf(PersonUiState())
        private set

    fun updateUiState(personDetail: PersonDetail) {
        uiState = PersonUiState(personDetail)
    }

    suspend fun insertPerson() {
        //personsRepository.insertPerson()
    }

}

data class PersonUiState(
    val personDetail: PersonDetail = PersonDetail()
)

data class PersonDetail(
    val firstName: String = "",
    val lastName: String = "",
    val cellphone: String = "",
    val residentialComplex: String = "",
    //val createdAt: Long = System.currentTimeMillis(),
    //val rolId: Int
)

fun PersonDetail.toEntityPerson() = Person(
    firstName = firstName,
    lastName = lastName,
    cellphone = cellphone.toIntOrNull() ?: 0,
    residentialComplex = residentialComplex,
    rolId = 1
)






