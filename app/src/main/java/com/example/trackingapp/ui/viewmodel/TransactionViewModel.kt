package com.example.trackingapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.data.TransactionDao
import com.example.trackingapp.ui.data.TransactionDatabase

import com.example.trackingapp.ui.repository.MoneyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel  constructor(application: Application) : AndroidViewModel(application) {

     val getExpense : LiveData<List<Transaction>>
    private var repository : MoneyRepository

    init {
        val moneyDao = TransactionDatabase.getDatabase(application).moneyDao()
        repository = MoneyRepository(moneyDao)
        getExpense = repository.getExpense
    }

     fun addTransaction(transaction : Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)
        }
    }


}