package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface MoneyDao {


    @Insert(onConflict = IGNORE)
    suspend fun insert(category: Money)

    @Delete
    suspend fun delete(category: Money)

    @Query("DELETE  FROM money_table ")
    suspend fun deleteAll()

    @Query("SELECT Sum(Expense)  FROM money_table ")
     fun getExpense() : LiveData<List<Money>>


}