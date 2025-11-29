package com.henrydev.welcomehome.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(person: Person)

    @Query("SELECT * FROM persons ORDER BY first_name ASC")
    fun getAllPersons(): Flow<List<Person>>


}