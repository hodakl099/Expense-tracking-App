package com.example.trackingapp.ui.repository

import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.MoneyDao
import com.example.trackingapp.ui.data.Money

class MoneyRepository(private val moneyDao: MoneyDao) {

    val getExpense : LiveData<List<Money>> = moneyDao.getExpense()

    suspend fun addMoney(money: Money){
        moneyDao.insert(money)
    }


}