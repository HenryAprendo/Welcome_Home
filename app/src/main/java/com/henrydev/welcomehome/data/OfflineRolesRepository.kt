package com.henrydev.welcomehome.data

import kotlinx.coroutines.flow.Flow

class OfflineRolesRepository(private val rolDao: RolDao): RolesRepository {

    override suspend fun insertAllRoles(roles: List<Rol>) =
        rolDao.insertAll(roles)

    override fun getAllRolesStream(): Flow<List<Rol>> =
        rolDao.getAllRoles()

}