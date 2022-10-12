package com.example.trackingapp.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.entity.Transaction
import com.example.trackingapp.ui.data.TransactionDatabase
import com.example.trackingapp.ui.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TransactionViewModel  constructor(application: Application) : AndroidViewModel(application) {



    private val Context.limitDataStore by preferencesDataStore("expense_limit")

    private val Context.UIdataStore by preferencesDataStore("UI_preference")
    private val UIdataStore = getApplication<Application>().UIdataStore

    private val limitDataStore = getApplication<Application>().limitDataStore

    private val _isWarningIsGone = MutableLiveData(false)
    var isWarningIsGone = _isWarningIsGone

     val getTransactionExpense : LiveData<List<Transaction>>
     val getTransactionIncome : LiveData<List<Transaction>>
     val getAllTransaction : LiveData<List<Transaction>>

    private var repository : TransactionRepository

    init {
        val transactionDatabase = TransactionDatabase.getDatabase(application)
        repository = TransactionRepository(transactionDatabase)
        getTransactionExpense = repository.getTransactionByType("Expense")
        getTransactionIncome = repository.getTransactionByType("Income")
        getAllTransaction = repository.getAllSingleTransaction("Overall")
    }

    suspend fun readUIPreference(key: String): Boolean? {
        val dataStoreKey = booleanPreferencesKey(key)
        val preferences = UIdataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun saveUIPreference(key: String, value: Boolean) {
        val preferenceKey = booleanPreferencesKey(key)
        UIdataStore.edit {
            it[preferenceKey] = value
        }
    }



    // to add new transaction
     fun addTransaction(transaction : Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(transaction)
        }
    }  // to add new transaction

    fun deleteTransaction(transaction: Transaction) =
        viewModelScope.launch { repository.delete(transaction) }

    fun updateTransaction(transaction: Transaction) =
        viewModelScope.launch { repository.update(transaction) }

    fun deleteAllTransactions() =
        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteAllTransactions()
        }



    fun getTransactionById(id: Int) : LiveData<Transaction> = repository.getTransactionById(id)


    suspend fun setLimit(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        limitDataStore.edit { data ->
            data[preferenceKey] = value
        }
    }

    suspend fun getLimit(key: String): Int? {
        val dataStoreKey = intPreferencesKey(key)
        val preferences = limitDataStore.data.first()
        return preferences[dataStoreKey]
    }







}