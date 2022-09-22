package com.example.trackingapp.ui.repository

import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.AmountTransaction
import com.example.trackingapp.ui.data.AmountTransactionDao

class MoneyRepository(private val transactionDao: AmountTransactionDao) {

    val getExpense : LiveData<List<AmountTransaction>> = transactionDao.getExpense()

    suspend fun addTransaction(transaction: AmountTransaction){
        transactionDao.insert(transaction)
    }


}