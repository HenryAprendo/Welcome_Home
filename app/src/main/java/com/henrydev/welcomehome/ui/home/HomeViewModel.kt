package com.henrydev.welcomehome.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.welcomehome.data.Person
import com.henrydev.welcomehome.data.PersonsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    personsRepository: PersonsRepository
): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        personsRepository.getAllStreamPerson()
            .map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = TIME_OUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        const val TIME_OUT_MILLIS = 5_000L
    }

}

data class HomeUiState(
    val persons: List<Person> = listOf()
)