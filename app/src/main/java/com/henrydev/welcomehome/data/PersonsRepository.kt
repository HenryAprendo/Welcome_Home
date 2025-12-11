package com.henrydev.welcomehome.data

import kotlinx.coroutines.flow.Flow

interface PersonsRepository {

    suspend fun insertPerson(person: Person)

    suspend fun deletePerson(person: Person)

    fun getAllStreamPerson(): Flow<List<Person>>

    fun getPersonStream(id:Int): Flow<Person?>

}