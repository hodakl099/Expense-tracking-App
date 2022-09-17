package com.example.trackingapp.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category_table")
data class Category(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "Expense") val Expense : String?,
    @ColumnInfo(name = "Income") val Income : String?

)
