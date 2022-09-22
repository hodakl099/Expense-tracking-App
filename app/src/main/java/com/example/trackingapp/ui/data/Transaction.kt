package com.example.trackingapp.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "Income") val Income : Double?,
    @ColumnInfo(name = "Expense") val Expense : Double?,


)
