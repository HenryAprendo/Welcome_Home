package com.henrydev.welcomehome.data

import kotlinx.coroutines.flow.Flow

class OfflinePersonRepository(private val personDao: PersonDao) : PersonsRepository {

    override suspend fun insertPerson(person: Person) = personDao.insert(person)

    override suspend fun deletePerson(person: Person) = personDao.delete(person)

    override suspend fun updatePerson(person: Person) = personDao.update(person)

    override fun getAllStreamPerson(): Flow<List<Person>> = personDao.getAllPersons()

    override fun getPersonStream(id: Int): Flow<Person?> = personDao.getPerson(id)

}