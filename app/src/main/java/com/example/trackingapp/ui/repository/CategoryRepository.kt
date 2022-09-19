package com.example.trackingapp.ui.repository

import com.example.trackingapp.ui.data.Category
import com.example.trackingapp.ui.data.CategoryDao

class CategoryRepository(private val categoryDao: CategoryDao) {

    suspend fun addCategory(category: Category){
        categoryDao.insert(category)
    }

}