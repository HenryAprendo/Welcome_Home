package com.henrydev.welcomehome

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.henrydev.welcomehome.ui.home.HomeViewModel
import com.henrydev.welcomehome.ui.screen.EditPersonViewModel
import com.henrydev.welcomehome.ui.screen.PersonDetailViewModel
import com.henrydev.welcomehome.ui.screen.PersonEntryViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(personApplication().container.personsRepository)
        }

        initializer {
            PersonEntryViewModel(
                personApplication().container.personsRepository,
                personApplication().container.rolesRepository
            )
        }

        initializer {
            PersonDetailViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                personApplication().container.rolesRepository,
                personApplication().container.personsRepository
            )
        }

        initializer {
            EditPersonViewModel(
                saveStateHandle = this.createSavedStateHandle(),
                personApplication().container.personsRepository,
                personApplication().container.rolesRepository
            )
        }

    }

}

fun CreationExtras.personApplication(): PersonApplication {
    return (this[AndroidViewModelFactory.APPLICATION_KEY] as PersonApplication)
}