package com.example.trackingapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.data.TransactionDatabase
import com.example.trackingapp.ui.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel  constructor(application: Application) : AndroidViewModel(application) {

     val getExpense : LiveData<List<Transaction>>
    private var repository : TransactionRepository

    init {
        val amountTransaction = TransactionDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(amountTransaction)
        getExpense = repository.getExpense
    }

     fun addTransaction(transaction : Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)
        }
    }


}