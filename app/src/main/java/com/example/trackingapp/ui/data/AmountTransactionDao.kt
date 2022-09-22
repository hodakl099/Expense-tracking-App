package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface AmountTransactionDao {


    @Insert(onConflict = IGNORE)
    suspend fun insert(transaction: AmountTransaction)

    @Delete
    suspend fun delete(transaction: AmountTransaction)

    @Query("DELETE  FROM amount_table ")
    suspend fun deleteAll()

    @Query("SELECT * FROM amount_table order by id DESC ")
     fun getExpense() : LiveData<List<AmountTransaction>>


}


