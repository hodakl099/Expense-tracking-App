package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {
//
//    @Query("SELECT * FROM category_table")
//    fun readALlData() : LiveData<List<Category>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(category: Money)




    @Delete
    suspend fun delete(category: Money)

    @Query("DELETE  FROM money_table ")
    suspend fun deleteAll()

    @Query("SELECT Expense FROM money_table ")
     fun getExpense() : LiveData<Money>


}