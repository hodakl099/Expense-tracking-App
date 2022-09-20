package com.example.trackingapp.ui.viewmodel

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.example.trackingapp.ui.data.CategoryDatabase
import com.example.trackingapp.ui.data.Money
import com.example.trackingapp.ui.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel  constructor(application: Application) : AndroidViewModel(application) {

     val getExpense : LiveData<Money>
    private var repository : CategoryRepository

    init {
        val categoryDao = CategoryDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        getExpense = repository.getExpense
    }

     fun addUser(category : Money){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(category)
        }
    }


}