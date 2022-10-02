package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface AmountTransactionDao {


    //to insert new transaction to all_transaction table
    @Insert(onConflict = REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    //to update  transaction in all_transaction table
    @Update(onConflict = REPLACE)
    suspend fun updateTransaction(transaction: Transaction)

    //used to delete transaction
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    //get all Transactions from all_transactions table
    @Query("SELECT * FROM all_transactions ")
     fun getAllTransactions() : LiveData<List<Transaction>>

    //gets a list of Transaction Type either expense or income
    @Query("SELECT * FROM all_transactions WHERE transactionType == :transactionType")
    fun getTransaction(transactionType : String) : LiveData<List<Transaction>>








}


