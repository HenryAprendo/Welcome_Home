package com.henrydev.welcomehome.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class, Rol::class],
    version = 1,
    exportSchema = true
)
abstract class PersonDatabase: RoomDatabase() {

    abstract val personDao: PersonDao

    companion object {

        @Volatile
        var instance: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    PersonDatabase::class.java,
                    "person_database"
                )
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }


}


















