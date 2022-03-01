package me.kzaman.dna_solution.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.kzaman.dna_solution.database.dao.UserDao
import me.kzaman.dna_solution.database.entities.UserEntities


@Database(
    entities = [
        UserEntities::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}