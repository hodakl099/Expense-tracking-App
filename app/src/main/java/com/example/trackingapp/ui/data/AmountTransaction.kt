package com.example.trackingapp.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity(tableName = "amount_table")
data class AmountTransaction(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "Expense") val Expense : Double = 00.0,
    @ColumnInfo(name = "Income") val Income : Double = 00.0,
)


