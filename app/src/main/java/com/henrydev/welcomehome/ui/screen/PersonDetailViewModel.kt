package com.henrydev.welcomehome.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.henrydev.welcomehome.data.PersonsRepository

class PersonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    personsRepository: PersonsRepository
): ViewModel() {

    val itemId = checkNotNull(savedStateHandle[PersonDetailDestination.itemIdArg])




}