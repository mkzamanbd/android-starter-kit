package me.kzaman.dna_solution.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.kzaman.dna_solution.database.entities.UserEntities


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ArrayList<UserEntities>): LongArray

    @Query("SELECT * FROM users")
    suspend fun getLocalProducts(): List<UserEntities>


    @Query("DELETE FROM users")
    suspend fun deleteProductTable()
}