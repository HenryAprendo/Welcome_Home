package com.henrydev.welcomehome.data

import kotlinx.coroutines.flow.Flow

interface RolesRepository {

    suspend fun insertAllRoles(roles: List<Rol>)

    fun getAllRolesStream(): Flow<List<Rol>>

}