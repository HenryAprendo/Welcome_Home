package com.henrydev.welcomehome.data

import kotlinx.coroutines.flow.Flow

interface PersonsRepository {

    suspend fun insertPerson(person: Person)

    fun getAllStreamPerson(): Flow<List<Person>>

}