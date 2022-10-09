package com.example.trackingapp.ui.data.converters

import androidx.room.TypeConverter
import com.example.trackingapp.ui.utils.TransactionCategory

class Converter {

    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategory):String {
        return "${category.categoryIcon},${category.categoryDescription}"
    }

    @TypeConverter
    fun toTransactionCategory(value:String): TransactionCategory {
        val list = value.split(",")
        val description = list[0].toInt()
        val icon = list[1].toInt()
        return TransactionCategory(icon,description)
    }

}