package com.example.trackingapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.entity.Transaction
import com.example.trackingapp.ui.data.TransactionDatabase
import com.example.trackingapp.ui.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel  constructor(application: Application) : AndroidViewModel(application) {


    private val _transactionFilter = MutableLiveData("Overall")
    val transactionFilter: MutableLiveData<String> = _transactionFilter

     val getTransactionExpense : LiveData<List<Transaction>>
     val getTransactionIncome : LiveData<List<Transaction>>
     val getAllTransaction : LiveData<List<Transaction>>
    private var repository : TransactionRepository

    init {
        val transactionDatabase = TransactionDatabase.getDatabase(application)
        repository = TransactionRepository(transactionDatabase)
        getTransactionExpense = repository.getTransactionByType("EXPENSE")
        getTransactionIncome = repository.getTransactionByType("INCOME")
        getAllTransaction = repository.getAllSingleTransaction("Overall")
    }


    // to add new transaction
     fun addTransaction(transaction : Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(transaction)
        }
    }

    // to add new transaction
    fun getTransactionById(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTransactionById(id)
        }
    }







}