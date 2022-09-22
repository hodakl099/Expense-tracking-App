package com.example.trackingapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.AmountTransaction
import com.example.trackingapp.ui.data.TransactionDatabase
import com.example.trackingapp.ui.repository.MoneyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel  constructor(application: Application) : AndroidViewModel(application) {

     val getExpense : LiveData<List<AmountTransaction>>
    private var repository : MoneyRepository

    init {
        val amountTransaction = TransactionDatabase.getDatabase(application).moneyDao()
        repository = MoneyRepository(amountTransaction)
        getExpense = repository.getExpense
    }

     fun addTransaction(transaction : AmountTransaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)
        }
    }


}