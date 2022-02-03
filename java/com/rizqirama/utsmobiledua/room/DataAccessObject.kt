package com.rizqirama.utsmobiledua.room

import androidx.room.*
import com.rizqirama.utsmobiledua.model.Data
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface DataAccessObject {
    @Insert(onConflict = REPLACE)
    fun insert(data : Data)

    @Delete
    fun delete(data : Data)

    @Update
    fun update(data: Data): Int

    @Query("SELECT * FROM data ORDER BY id ASC")
    fun getAll() : List<Data>

    @Query("SELECT * FROM data ORDER BY id = :id LIMIT 1")
    fun getById(id: Int): Data

    @Query("DELETE FROM data")
    fun deleteAll(): Int


}