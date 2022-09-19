package com.example.trackingapp.ui.viewmodel

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.Category
import com.example.trackingapp.ui.data.CategoryDao
import com.example.trackingapp.ui.data.CategoryDatabase
import com.example.trackingapp.ui.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel  constructor(application: Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<Category>>
    private var repository : CategoryRepository

    init {
        val categoryDao = CategoryDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        readAllData = repository.readAllData
    }

     fun addUser(category : Category){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(category)
        }
    }


}