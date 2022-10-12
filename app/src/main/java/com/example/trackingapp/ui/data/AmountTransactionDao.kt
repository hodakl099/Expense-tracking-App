package com.example.trackingapp.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.trackingapp.ui.data.entity.Transaction

@Dao
interface TransactionDao {


    //to insert new transaction to all_transaction table
    @Insert(onConflict = REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    //to update  transaction in all_transaction table
    @Update
    suspend fun updateTransaction(transaction: Transaction)

    //used to delete transaction
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)


    //get all Transactions from all_transactions table
    @Query("SELECT * FROM all_transactions ")
     fun getAllTransactions() : LiveData<List<Transaction>>


    //delete all Transactions from all_transactions table
    @Query("DELETE  FROM all_transactions ")
    fun deleteAllTransactions()

    //gets a list of Transaction Type either expense or income
    @Query("SELECT * FROM all_transactions WHERE transactionType == :transactionType")
    fun getAllTransactionsByType(transactionType : String) : LiveData<List<Transaction>>

    // get single transaction by id
    @Query("SELECT * FROM all_transactions WHERE id = :id")
    fun getTransactionByID(id: Int): LiveData<Transaction>










}


