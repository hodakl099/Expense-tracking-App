package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface TransactionDao {


    @Insert(onConflict = IGNORE)
    suspend fun insert(category: Transaction)

    @Delete
    suspend fun delete(category: Transaction)

    @Query("DELETE  FROM transaction_table ")
    suspend fun deleteAll()

    @Query("SELECT Sum(Expense)  FROM transaction_table ")
     fun getExpense() : LiveData<List<Transaction>>


}