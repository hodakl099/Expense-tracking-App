package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface TransactionDao {


    @Insert(onConflict = IGNORE)
    suspend fun insert(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("DELETE  FROM transaction_table ")
    suspend fun deleteAll()

    @Query("SELECT * FROM transaction_table order by id DESC ")
     fun getExpense() : LiveData<List<Transaction>>


}


