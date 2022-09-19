package com.example.trackingapp.ui.repository

import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.Category
import com.example.trackingapp.ui.data.CategoryDao

class CategoryRepository(private val categoryDao: CategoryDao) {

    val readAllData : LiveData<List<Category>> = categoryDao.readALlData()

    suspend fun addCategory(category: Category){
        categoryDao.insert(category)
    }

}