package com.example.trackingapp.ui.repository


import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.data.TransactionDatabase

class TransactionRepository(private val database: TransactionDatabase) {


    // insert transaction
    suspend fun insert(transaction: Transaction) = database.transactionDao().insertTransaction(transaction)

    // update transaction
    suspend fun update(transaction: Transaction) = database.transactionDao().updateTransaction(transaction)

    // delete transaction
    suspend fun delete(transaction: Transaction) = database.transactionDao().deleteTransaction(transaction)

    // get all transaction
    fun getAllTransaction() = database.transactionDao().getAllTransactions()

    // get single transaction by type either income or expense
    fun getTransactionByType(transactionType: String) = database.transactionDao().getAllTransactionsByType(transactionType)

    // get single transaction type - Expense or Income or else overall
    fun getAllSingleTransaction(transactionType: String) = if (transactionType == "Overall") {
        getAllTransaction()
    } else {
        database.transactionDao().getAllTransactionsByType(transactionType)
    }



}