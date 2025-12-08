package com.henrydev.welcomehome.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val personId: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val cellphone: Long,
    @ColumnInfo(name = "residential_complex")
    val residentialComplex: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    val rolId: Int
)

@Entity(tableName = "roles")
data class Rol(
    @PrimaryKey
    val rolId: Int,
    val name: String
)

data class RolWithPerson(
    @Embedded val rol: Rol,
    @Relation(
        parentColumn = "rolId",
        entityColumn = "rolId"
    )
    val persons: List<Person>
)

