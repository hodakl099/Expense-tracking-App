package com.example.trackingapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.trackingapp.ui.data.Category

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<Category>>

    init {
        val categoryDao =
    }


}