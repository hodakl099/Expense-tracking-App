package com.example.trackingapp.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "Expense") val Expense : Double,
)
