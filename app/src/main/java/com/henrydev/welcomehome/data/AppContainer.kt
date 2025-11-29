package com.henrydev.welcomehome.data

import android.content.Context

interface AppContainer {

    val personsRepository: PersonsRepository

}


class DefaultAppContainer(context: Context) : AppContainer {

    override val personsRepository: PersonsRepository by lazy {
        OfflinePersonRepository(PersonDatabase.getDatabase(context).personDao)
    }

}