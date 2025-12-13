package com.henrydev.welcomehome.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(roles: List<Rol>)

    @Query("SELECT * FROM roles ORDER BY name ASC")
    fun getAllRoles(): Flow<List<Rol>>

    @Query("SELECT * FROM roles WHERE rolId = :id")
    fun getRol(id: Int): Flow<Rol?>

}