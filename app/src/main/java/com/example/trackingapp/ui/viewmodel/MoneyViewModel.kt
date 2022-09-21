package com.example.trackingapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.example.trackingapp.ui.data.MoneyDatabase
import com.example.trackingapp.ui.data.Money
import com.example.trackingapp.ui.repository.MoneyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyViewModel  constructor(application: Application) : AndroidViewModel(application) {

     val getExpense : LiveData<List<Money>>
    private var repository : MoneyRepository

    init {
        val moneyDao = MoneyDatabase.getDatabase(application).moneyDao()
        repository = MoneyRepository(moneyDao)
        getExpense = repository.getExpense
    }

     fun addMoney(money : Money){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMoney(money)
        }
    }


}