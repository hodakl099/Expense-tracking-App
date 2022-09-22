package com.example.trackingapp.ui.repository

import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.data.TransactionDao

class MoneyRepository(private val transactionDao: TransactionDao) {

    val getExpense : LiveData<List<Transaction>> = transactionDao.getExpense()

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.insert(transaction)
    }


}