package com.henrydev.welcomehome.data

import android.content.Context

interface AppContainer {
    val personsRepository: PersonsRepository
    val rolesRepository: RolesRepository
}


class DefaultAppContainer(context: Context) : AppContainer {

    override val personsRepository: PersonsRepository by lazy {
        OfflinePersonRepository(PersonDatabase.getDatabase(context).personDao)
    }

    override val rolesRepository: RolesRepository by lazy {
        OfflineRolesRepository(PersonDatabase.getDatabase(context).rolDao)
    }

}