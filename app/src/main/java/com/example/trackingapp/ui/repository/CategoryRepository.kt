package com.example.trackingapp.ui.repository

import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.CategoryDao
import com.example.trackingapp.ui.data.Money

class CategoryRepository(private val categoryDao: CategoryDao) {

//    val readAllData : LiveData<List<Category>> = categoryDao.readALlData()
    val getExpense : LiveData<Money> = categoryDao.getExpense()

    suspend fun addCategory(category: Money){
        categoryDao.insert(category)
    }


}