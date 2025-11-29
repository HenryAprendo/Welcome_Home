package com.henrydev.welcomehome

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.henrydev.welcomehome.ui.home.HomeViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(personApplication().container.personsRepository)
        }

    }

}


fun CreationExtras.personApplication(): PersonApplication {
    return (this[AndroidViewModelFactory.APPLICATION_KEY] as PersonApplication)
}