package com.example.trackingapp.ui.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import java.nio.charset.CodingErrorAction.REPLACE
import androidx.room.OnConflictStrategy.REPLACE as OnConflictStrategyREPLACE

@Dao

interface CategoryDao {

    @Query("SELECT * FROM category_table")
    fun getAll() : List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE  FROM category_table ")
    suspend fun deleteAll()


}