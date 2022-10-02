package com.example.trackingapp.ui.repository

import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.data.AmountTransactionDao

class MoneyRepository(private val transactionDao: AmountTransactionDao) {

    val getExpense : LiveData<List<Transaction>> = transactionDao.getExpense()

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.insert(transaction)
    }


}