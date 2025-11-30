package com.henrydev.welcomehome.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Person::class, Rol::class],
    version = 1,
    exportSchema = true
)
abstract class PersonDatabase: RoomDatabase() {

    abstract val personDao: PersonDao
    abstract val rolDao: RolDao

    companion object {

        @Volatile
        var Instance: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    PersonDatabase::class.java,
                    "person_database"
                )
                    .addCallback(DatabaseCallback(context))
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }

        /**
         * Callback
         */
        private class DatabaseCallback(
            private val context: Context
        ): Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Instance?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.rolDao)
                    }
                }
            }

        }

        suspend fun populateDatabase(rolDao: RolDao) {
            val roles = listOf(
                Rol(rolId = 1, name = "Member"),
                Rol(rolId = 2, name = "Visitor")
            )
            rolDao.insertAll(roles)
        }

    }

}






































